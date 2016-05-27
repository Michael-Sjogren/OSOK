package osok.network.server;

import application.Bank;
import application.Player;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Socket csocket;
    private static ArrayList<Socket> clients = new ArrayList<>();
    private static ArrayList<Player> players = new ArrayList<>();


    public static void main(String args[]) {

        try {

            ServerSocket ssock = new ServerSocket(55556);
            System.out.println("isServerSocket closed : " + ssock.isClosed());

            while (true) {

                if (clients.size() < 5) {
                    csocket = ssock.accept();
                    new Thread(new ServerRead(csocket)).start();
                    new Thread(new ServerWrite(csocket)).start();
                    System.out.println("client connected" + "");
                    clients.add(csocket);
                    players.add(null);
                } else if (clients.size() >= 5) {
                    System.out.println("player max limit");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerRead implements Runnable {
        private Socket csocket;
        private Gson gson;
        private Player player;

        public ServerRead(Socket csocket) {
            this.csocket = csocket;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(csocket.getInputStream()))) {
                gson = new Gson();
                while (true) {
                    Thread.sleep(1);
                    String object = br.readLine();
                    player = gson.fromJson(object, Player.class);
                    for (int i = 0; i < clients.size(); i++) {
                        if (csocket.getPort() == clients.get(i).getPort()) {
                            players.set(i, player);
                        }
                    }
                }
            } catch (IOException e) {
                closeSocket(csocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Writes to Clients
     **/
    static class ServerWrite implements Runnable {

        private Socket csocket;

        ServerWrite(Socket csocket) {
            this.csocket = csocket;
        }

        public void run() {
            try (PrintWriter pw = new PrintWriter(csocket.getOutputStream())) {

                while (true) {
                    Thread.sleep(1);
                    pw.println(stringifiyInfo(players , clients , csocket));
                    pw.flush();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
                closeSocket(csocket);
            }
        }

        public String stringifiyInfo(ArrayList<Player> players, ArrayList<Socket> clients, Socket currentSocket) {
            ArrayList<String> tempArray = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < clients.size(); i++){

                if(!(clients.get(i) == currentSocket)){

                    tempArray.add(gson.toJson(players.get(i)));
                 //   System.out.println(gson.toJson(players.get(i)));
                }
            }
         //   System.out.println(gson.toJson(tempArray));
            return gson.toJson(tempArray);
        }
    }

    public static void closeSocket(Socket csocket){
        for (int i = 0; i < clients.size(); i++){
            System.out.println("checking connectons");
            if (clients.get(i).isClosed() == true){

                System.out.println(clients.get(i).getLocalAddress());
                clients.remove(i);
                players.remove(i);
                System.out.println("connection closed");
            }
        }
    }
}


/**
 * Reads Client
 **/


