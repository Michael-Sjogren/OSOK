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
    private int port;
    private boolean running;
    private ChatRead read;
    private ChatWrite write;
    private Thread chatThreadRead , chatThreadWrite;
    private Socket socket;

    public ChatClient(int port ,String ip , Bank bank){
        this.port = port;
        this.ip = ip;

        System.out.println("chat client called");
        try  {
            Socket socket = new Socket(ip , port);
            this.socket = socket;
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

}


class ChatWrite implements Runnable {

    private Socket socket;
    private Bank bank;
    private boolean running = true;

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

            while(running){
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
}

class ChatRead implements Runnable{



    private Bank bank;
    private Socket socket;
    private boolean running = true;
    private String newMessage;
    private String oldMessage = "";

    ChatRead(Socket socket, Bank bank){
        this.socket = socket;
        this.bank = bank;
    }



    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            System.out.println("client chat read running");


            String condition = bank.getPlayer().getUsername() + " : " + "null";
            while(running){
                newMessage = br.readLine();
                if(!newMessage.equals(condition) && !newMessage.equals(oldMessage)){
                    bank.getGui().getChatLog().appendText("\n"+newMessage);
                    oldMessage = newMessage;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
