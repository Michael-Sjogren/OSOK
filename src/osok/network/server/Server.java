package osok.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket csocket;
    private static ServerRead read;
    private static ServerWrite write;
    private static Thread threadRead;
    private static Thread threadWrite;


    public static void main(String args[]) {

        try {

            ServerSocket ssock = new ServerSocket(55556);
            System.out.println("isServerSocket closed : " + ssock.isClosed());
            while (true) {

                csocket = ssock.accept();
                new Thread(new ServerRead(csocket)).start();
                new Thread(new ServerWrite(csocket)).start();
                System.out.println("client connected" + "");

            }


        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }


    /** Reads Client **/
     class ServerRead implements Runnable {
        private Socket csocket;
        public ServerRead(Socket csocket) {
            this.csocket = csocket;
        }

        @Override
        public void run() {
            try(ObjectInputStream ois = new ObjectInputStream(csocket.getInputStream())) {

                while(true){
                    Thread.sleep(1000);
                    String s = (String) ois.readObject();
                    System.out.println(s);

                }
            }
            catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
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
            try(ObjectOutputStream oos = new ObjectOutputStream(csocket.getOutputStream())) {


                while(true){
                   Thread.sleep(1000);
                   oos.writeObject("SERVER :: Writing to Client");

                }
            }
            catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
