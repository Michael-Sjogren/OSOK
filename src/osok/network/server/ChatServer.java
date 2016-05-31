package osok.network.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Michael Sjögren on 2016-05-30.
 */
public class ChatServer extends Thread{

    private boolean running = true;
    private ServerChatRead sChatRead;
    private ServerChatWrite sChatWrite;
    private Thread sChatThreadRead , sChatThreadWrite;
    private ServerMessageHandler handler;

    public ChatServer(){
       start();
    }

    public void run(){
        System.out.println("chat server listening");
        try (ServerSocket chatServerSocket = new ServerSocket(55555)){
            handler = new ServerMessageHandler();
            while(running){
                Socket socket = chatServerSocket.accept();
                System.out.println(" ---- chat client connected ----- " +  socket.getPort());
                sChatRead = new ServerChatRead(socket , handler);
                sChatWrite = new ServerChatWrite(socket , handler);
                sChatThreadRead = new Thread(sChatRead);
                sChatThreadWrite = new Thread(sChatWrite);
                sChatThreadRead.start();
                sChatThreadWrite.start();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

/** SERVER CHAT WRITE CLASS **/
class ServerChatWrite implements Runnable {

    private Socket socket;
    private boolean running = true;
    private ServerMessageHandler handler;

    ServerChatWrite(Socket socket , ServerMessageHandler handler){
        this.socket = socket;
        this.handler = handler;
    }

    @Override
    public void run() {
        try(PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
            String newMessage;
            String oldMessage = "";

            while(running){
                Thread.sleep(200);
                    pw.println(handler.getMessage());
                    pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/** SERVER CHAT WRITE CLASS **/
class ServerChatRead implements Runnable {

    private Socket socket;
    private ServerMessageHandler handler;
    private String message = "";

    ServerChatRead(Socket socket , ServerMessageHandler handler){
        this.socket = socket;
        this.handler = handler;
    }


    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            String newMessage;
            String oldMessage = "";
            while(true){
                Thread.sleep(200);
                newMessage = br.readLine();

                try {
                    if(!newMessage.equals(oldMessage)){
                        handler.setMessage(newMessage);
                        oldMessage = newMessage;
                    }
                }catch (Exception e){

                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ServerMessageHandler {

    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
