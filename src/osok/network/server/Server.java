package osok.network.server;

import application.Bullet;
import application.Player;
import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Socket csocket;
    private static ArrayList<Socket> clients = new ArrayList<>();
    private static ArrayList<Object> players = new ArrayList<>();
    private static ArrayList<Object> bullets = new ArrayList<>();


    public static void main(String args[]) {
    	
        try {
        	
            // creates a server socket that listens on port 555556
            ServerSocket ssock = new ServerSocket(55556);
            System.out.println("Server starting on port: "+ssock.getLocalPort());
            while (true) {
                // if client array is less than 5 in size you can connect
                if (clients.size() < 5) {
                    // server listens connection requests , when a connection is created threads gets created
                    csocket = ssock.accept();
                    System.out.println("client connected port : " + csocket.getPort());
                    new Thread(new ServerRead(csocket)).start();
                    new Thread(new ServerWrite(csocket)).start();

                    // fills array so we can set it later on
                    clients.add(csocket);
                    players.add(null);
                    bullets.add(null);

                    new ChatServer();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                csocket.close();
                closeSocket(csocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** reads from client **/
    static class ServerRead implements Runnable {
        private BufferedReader br;
        private Socket csocket;
        private Gson gson;
        private Player player;
        private Bullet bullet;
        private String bulletString;

        /** Constructor **/
        public ServerRead(Socket csocket) {
            this.csocket = csocket;
        }

        @Override
        public void run() {
            try  {
                // creates a new inputstream
                br = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
                // gson object for deSerialization and serialization of pojo class , Bullet and Player
                gson = new Gson();
                String object;

                while (true) {
                    Thread.sleep(1);

                    object = br.readLine();
                    bulletString = br.readLine();
                    // deSerializes gson String into object
                    player = gson.fromJson(object, Player.class);
                    bullet = gson.fromJson(bulletString, Bullet.class);
                    // if current socket is equal to the socket in array , exclude that index (i) and set player and bullet array
                    for (int i = 0; i < clients.size(); i++) {
                        if (csocket == clients.get(i)) {
                            players.set(i, player);
                            bullets.set(i, bullet);
                        }
                    }
                }
            } catch (IOException e) {

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                    closeSocket(csocket);
                    csocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** writes to client **/
    static class ServerWrite implements Runnable {

        private PrintWriter pw;
        private Socket csocket;

        ServerWrite(Socket csocket) {
            this.csocket = csocket;
        }

        public void run() {
            try  {
                pw = new PrintWriter(csocket.getOutputStream());
                while (true) {
                    Thread.sleep(1);
                    // sends arrays and a socket for comparing. To a method witch serializes the arrays to strings
                    pw.println(stringifiyInfo(players, clients, csocket));
                    pw.flush();
                    pw.println(stringifiyInfo(bullets, clients, csocket));
                    pw.flush();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    pw.close();
                    csocket.close();
                    closeSocket(csocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /** serializes elements into arrays witch returns that array when serialization is complete  **/
        public String stringifiyInfo(ArrayList<Object> array, ArrayList<Socket> clients, Socket currentSocket) {
            // a temporary array created
            ArrayList<String> tempArray = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < clients.size(); i++) {
                if (!(clients.get(i) == currentSocket)) {
                    tempArray.add(gson.toJson(array.get(i)));
                }
            }
            // send back the array
            return gson.toJson(tempArray);
        }
    }
/**  if close window this method is called , which removes the element that is equal to current socket **/
    public static void closeSocket(Socket socket) {

        players.remove(clients.indexOf(socket));
        bullets.remove(clients.indexOf(socket));
        clients.remove(clients.indexOf(socket));
    }

}


