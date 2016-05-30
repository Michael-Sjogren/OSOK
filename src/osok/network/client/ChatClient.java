package osok.network.client;

import application.Bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Michael Sjögren on 2016-05-30.
 */
public class ChatClient {

    private String ip;
    private Bank bank;
    private int port;
    private ChatRead read;
    private ChatWrite write;
    private Thread chatThreadRead;
    private Thread chatThreadWrite;
    private Socket socket;
    private Client client;




    public ChatClient(String ip, Bank bank){
        this.ip = ip;
        this.bank = bank;

    }


    public void startChatClient(){
        try  {
            socket = new Socket(ip ,55555);
            System.out.println("is chat client socket closed? :: "+socket.isClosed() + " ip : " + ip + "port : " + port);
            read = new ChatRead(socket , bank);
            write = new ChatWrite(socket , bank);

            chatThreadRead = new Thread(read);
            chatThreadWrite = new Thread(write);
            chatThreadRead.start();
            chatThreadWrite.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Thread getChatThreadWrite() {
        return chatThreadWrite;
    }

    public Thread getChatThreadRead() {
        return chatThreadRead;
    }

    /** terminates chat client threads and jumps out of loop when closing window **/
    public void stopChat(){

        read.readSetIsRunning(false);
        write.writeSetIsRunning(false);

        try {
            chatThreadRead.join();
            chatThreadWrite.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class ChatWrite implements Runnable {

    private Socket socket;
    private Bank bank;
    private boolean isRunning = true;

    ChatWrite(Socket socket, Bank bank){
        this.socket = socket;
        this.bank = bank;
    }

    @Override
    public void run() {
        try(PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
            System.out.println("client chat write running");
            String newMessage;
            String oldMessage = "";

            while(isRunning){
                Thread.sleep(200);
                newMessage = bank.getPlayer().getMessage();
                pw.println(bank.getPlayer().getUsername() + " : " + newMessage);
                pw.flush();


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeSetIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}

class ChatRead implements Runnable{



    private Bank bank;
    private Socket socket;
    private String newMessage;
    private String oldMessage = "";
    private boolean isRunning = true;

    ChatRead(Socket socket, Bank bank){
        this.socket = socket;
        this.bank = bank;
    }

    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            System.out.println("client chat read running");
            boolean firstMessage = true;

            String condition = bank.getPlayer().getUsername() + " : " + "null";
            while(isRunning){
                newMessage = br.readLine();
                if(!newMessage.equals(condition) && !newMessage.equals(oldMessage)){
                    if(!firstMessage){
                        bank.getGui().getChatLog().appendText("\n"+newMessage);
                    }else {
                        bank.getGui().getChatLog().appendText(newMessage);
                        firstMessage = false;
                    }

                    oldMessage = newMessage;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void readSetIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
