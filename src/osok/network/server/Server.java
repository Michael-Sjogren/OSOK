package osok.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket csocket;

    Server(Socket csocket) {
        this.csocket = csocket;
    }

    public static void main(String args[]) {

        try {
            ServerSocket ssock = new ServerSocket(55556);
            System.out.println("isServerSocket closed : " + ssock.isClosed());
            while(true){
                csocket = ssock.accept();
                System.out.println("client connected" + "");
                new Thread(new ServerWrite(csocket) , "Server-write-thread").start();
                new Thread(new ServerRead(csocket) , "Server-read-thread").start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Reads Client **/
    private static class ServerRead implements Runnable {
       private Socket csocket;
        public ServerRead(Socket csocket) {
            this.csocket = csocket;
        }

        @Override
        public void run() {
            try(ObjectInputStream ois = new ObjectInputStream(csocket.getInputStream())) {

                while(true){
                    Thread.sleep(2000);
                    System.out.println("Reads from Client : --v");
                    System.out.println(ois.readUTF());
                    }
                }
                 catch (IOException e) {
                System.out.println(e);
                } catch (InterruptedException e) {
                e.printStackTrace();
                }
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
            try(ObjectOutputStream oos = new ObjectOutputStream(csocket.getOutputStream())) {
                System.out.println("Writing to Client");
                for (int i = 100; i >= 0; i--) {
                    oos.writeUTF(i +
                            " bottles of beer on the wall");
                }
                while(true){
                    Thread.sleep(17);
                }
            }
            catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
