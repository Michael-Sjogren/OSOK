package osok.network.server;

import application.Bank;
import application.Bullet;
import application.Player;
import com.google.gson.Gson;
import osok.network.client.ChatClient;

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

            ServerSocket ssock = new ServerSocket(55556);
            System.out.println("isServerSocket closed : " + ssock.isClosed());
            while (true) {

                if (clients.size() < 5) {
                    csocket = ssock.accept();
                    System.out.println("client connected port : " + csocket.getPort());
                    new Thread(new ServerRead(csocket)).start();
                    new Thread(new ServerWrite(csocket)).start();
                    clients.add(csocket);
                    players.add(null);
                    bullets.add(null);

                    new ChatServer();
                } else if (clients.size() >= 5) {
                }
            }
        } catch (IOException e) {
            closeSocket();
            e.printStackTrace();
        }
    }

    static class ServerRead implements Runnable {
        private Socket csocket;
        private Gson gson;
        private Player player;
        private Bullet bullet;
        private String bulletString;

        public ServerRead(Socket csocket) {
            this.csocket = csocket;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(csocket.getInputStream()))) {
                gson = new Gson();
                String object;

                while (true) {
                    Thread.sleep(1);

                    object = br.readLine();
                    bulletString = br.readLine();
                    player = gson.fromJson(object, Player.class);
                    bullet = gson.fromJson(bulletString, Bullet.class);

                    for (int i = 0; i < clients.size(); i++) {
                        if (csocket.getPort() == clients.get(i).getPort()) {
                            players.set(i, player);
                            bullets.set(i, bullet);
                        }
                    }
                }

            } catch (IOException e) {
                closeSocket();

            } catch (Exception e) {
                closeSocket();
                e.printStackTrace();
            }
        }
    }


    /**
     * Writes to Clients
     **/
    static class ServerWrite implements Runnable {

        private Socket csocket;
        private ServerMessageHandler handler;

        ServerWrite(Socket csocket) {
            this.csocket = csocket;
        }

        public void run() {
            try (PrintWriter pw = new PrintWriter(csocket.getOutputStream())) {

                while (true) {
                    Thread.sleep(1);
                    pw.println(stringifiyInfo(players, clients, csocket));
                    pw.flush();
                    pw.println(stringifiyInfo(bullets, clients, csocket));
                    pw.flush();
                }

            } catch (IOException e) {
                closeSocket();
                System.out.println(e.getMessage());

            } catch (Exception e) {
                closeSocket();
                e.printStackTrace();
            }
        }

        public String stringifiyInfo(ArrayList<Object> array, ArrayList<Socket> clients, Socket currentSocket) {
            ArrayList<String> tempArray = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < clients.size(); i++) {

                if (!(clients.get(i) == currentSocket)) {
                    tempArray.add(gson.toJson(array.get(i)));
                }
            }
            return gson.toJson(tempArray);
        }
    }

    public static void closeSocket() {
        for (int i = 0; i < clients.size(); i++) {
            System.out.println("checking connectons");
            if (clients.get(i).isClosed() == true) {
                System.out.println("remove socket "  + i);
                clients.remove(i);
                players.remove(i);
                bullets.remove(i);
                System.out.println(clients.toString());

            }
        }
    }

}


/**
 * Reads Client
 **/


