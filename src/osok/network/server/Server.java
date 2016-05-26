package osok.network.server;

import application.Player;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Socket csocket;
    private static ArrayList<Socket> clients = new ArrayList<>();


    public static void main(String args[]) {

        try {

            ServerSocket ssock = new ServerSocket(55556);
            System.out.println("isServerSocket closed : " + ssock.isClosed());
            while (true) {
                if(clients.size() < 5){
                    csocket = ssock.accept();
                    new Thread(new ServerRead(csocket)).start();
                    new Thread(new ServerWrite(csocket)).start();
                    System.out.println("client connected" + "");
                    clients.add(csocket);
                }else if (clients.size() >= 5){
                    System.out.println("player max limit");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }


    /** Reads Client **/
     class ServerRead implements Runnable {
        private Socket csocket;
        private Gson gson;
        private Player player;

        public ServerRead(Socket csocket) {
            this.csocket = csocket;
        }

        @Override
        public void run() {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(csocket.getInputStream()))) {
                gson = new Gson();
                while(true){
                    Thread.sleep(1);
                     String object = br.readLine();
                     player = gson.fromJson(object,Player.class);
                }
            }
            catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Writes to Clients **/
    class ServerWrite implements Runnable{

        Socket csocket;

        ServerWrite(Socket csocket){
            this.csocket = csocket;
        }

        public void run() {
            try(PrintWriter pw = new PrintWriter(csocket.getOutputStream())) {

                while(true){
                   Thread.sleep(1000);
                    pw.println("SERVER :: Writing to Client");
                    pw.flush();
                }
            }
            catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
