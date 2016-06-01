package osok.network.client;

import application.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Michael Sjögren on 2016-05-30.
 */


/** class for starting the read and write threads of the chat client this class gets invoked
 * by calling the method startChatClient witch will start a connection and also create a read and write thread for chat **/
public class ChatClient {

    private String ip;
    private Controller con;
    private ChatRead read;
    private ChatWrite write;
    private Thread chatThreadRead;
    private Thread chatThreadWrite;
    private Socket socket;

    /** constructor **/
    public ChatClient(String ip, Controller con){
        this.ip = ip;
        this.con = con;

    }

    /** starts a connection and starts the threads for reading and writing to server is called in login **/
    public void startChatClient(){
        try  {
            socket = new Socket(ip ,55555);
            read = new ChatRead(socket , con);
            write = new ChatWrite(socket , con);

            chatThreadRead = new Thread(read);
            chatThreadWrite = new Thread(write);
            chatThreadRead.start();
            chatThreadWrite.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** terminates chat client threads and jumps out of loop when closing window **/
    public void stopChat(){
        read.readSetIsRunning(false);
        write.writeSetIsRunning(false);

        try {
            chatThreadRead.join();
            chatThreadWrite.join();
            System.out.println(" --- threads terminated chat ---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/** writes to server , receives socket from startChatClient method in constructor  ,
 *  also controller reference to get the text of the textfield for chat **/
class ChatWrite implements Runnable {

    private Socket socket;
    private Controller con;
    private boolean isRunning = true;

    /** Constructor **/
    ChatWrite(Socket socket, Controller con){
        this.socket = socket;
        this.con = con;
    }

    @Override
    public void run() {
        // creates a outputstream so we can write to server
        try(PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
            System.out.println("client chat write running");

            String newMessage;
            String oldMessage = "";

            while(isRunning){
                Thread.sleep(200);
                // gets message from textfield of chat
                newMessage = con.getPlayer().getMessage();
                // concatenates username with the message to show who's writing what
                pw.println(con.getPlayer().getUsername() + " : " + newMessage);
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /** setter for isRunning boolean **/
    public void writeSetIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}

/** reads to server , receives socket from startChatClient method in constructor ,
 * also controller reference to set the text of the chatLog (TextArea) for chat  **/
class ChatRead implements Runnable{

    private Controller con;
    private Socket socket;
    private String newMessage;
    private String oldMessage = "";
    private boolean isRunning = true;

    /** Constructor **/
    ChatRead(Socket socket, Controller con){
        this.socket = socket;
        this.con = con;
    }

    @Override
    public void run() {
        // creates a InputStream so it can read server response
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            System.out.println("client chat read running");
            boolean firstMessage = true;

            String condition = con.getPlayer().getUsername() + " : " + "null";
            while(isRunning){
                // receives message from other client
                newMessage = br.readLine();

                if(!newMessage.equals(condition) && !newMessage.equals(oldMessage)){
                    if(!firstMessage){
                        // sets message to textarea of each client
                        con.getGui().getChatLog().appendText("\n"+newMessage);
                    }else {
                        // sets message to textarea of each client
                        con.getGui().getChatLog().appendText(newMessage);
                        firstMessage = false;
                    }

                    oldMessage = newMessage;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void readSetIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
