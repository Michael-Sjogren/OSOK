package osok.network.client;


import application.Bank;
import application.Bullet;
import application.Player;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Michael Sjögren on 2016-05-20.
 */

/** Game Client starts thread when Client constructor is called (new Client) can be seen in application/login.java **/
public class Client{

    private ClientRead read;
    private ClientWrite write;
    private Thread writingThread;
    private Thread readingThread;
    private Socket socket;
    private Player player;

    /** Constructor **/
    public Client(Bank bank){
        // gets player reference that's is instantiated in controller
        this.player = bank.getPlayer();

        try {
            // creates a new socket for game client
           Socket socket = new Socket( bank.getPlayer().getIp() ,bank.getPlayer().getPort());
           System.out.println(" -- Client connected -- ");

            if(socket.isClosed()){
                return;
            }else{
                // sends to player that he is connected ,
                // used in login to determine whether or no connection was successful if true you get into game else nothing happens
                player.setIsConnected(true);
            }
           this.socket = socket;
        } catch (IOException e) {
            e.printStackTrace();
        }


        /* start write thread for client */
        write = new ClientWrite(socket , bank );
        writingThread = new Thread(write , " client-write-thread ");
        writingThread.start();

        /* start read thread for client */
        read = new ClientRead(socket,bank);
        readingThread = new Thread(read , "  client-read-thread ");
        readingThread.start();
    }

    /** terminates threads when called**/
    public void shutdown()  {
            read.isRunning(false);
            write.isRunning(false);
            try {

                    readingThread.join();
                    writingThread.join();
                    System.out.println("-- client all threads terminated --");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}

    /** reads response from server and receives string of a json object which contains player and bullet object **/
    class ClientRead implements Runnable{

            private BufferedReader br;
            private Socket socket;
            private Bank bank;
            private volatile boolean running = true;
            private  ArrayList<String> oldPlayers;
            private ArrayList<Player> newPlayers = new ArrayList<>();
            private ArrayList<String> oldBullets;
            private ArrayList<Bullet> newBullets = new ArrayList<>();

        /** Constructor **/
        public ClientRead(Socket socket, Bank bank){
                this.socket = socket;
                this.bank=bank;
                }

        @Override
        public void run(){
                    try  {
                        // creates a input stream so that we can read from server
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // creates a Gson object to deSerialize objects
                        Gson gson = new Gson();

                         while(running){
                             Thread.sleep(1);
                             // receives ArrayList of players and bullets for each player
                             oldPlayers = gson.fromJson( br.readLine() , ArrayList.class);
                             oldBullets = gson.fromJson( br.readLine() , ArrayList.class);
                             // clears array
                             newBullets.clear();
                             newPlayers.clear();
                             try{
                                 // gets all players from old array and puts the into new array since array size may differ
                                 // depending on how many clients are connected
                                 for(int i = 0; i < oldPlayers.size(); i++){
                                     newPlayers.add(gson.fromJson(oldPlayers.get(i), Player.class));
                                    }
                                 for(int i = 0; i < oldBullets.size(); i++){
                                     newBullets.add(gson.fromJson(oldBullets.get(i), Bullet.class));
                                 }
                                 // sends new arrays to opponent class
                                    bank.getOpponents().setOpponentsList(newPlayers);
                                    bank.getOpponents().setBulletList(newBullets);

                             }catch (Exception e){
                                e.printStackTrace();
                             }
                         }
                    }catch (Exception e){
                    e.printStackTrace();
                }finally {
                        try {
                            br.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }
                    // setter for isRunning
                    public void isRunning(boolean running) {
                    this.running = running;
                    }
     }

    /** writes to server **/
    class ClientWrite implements Runnable{

                private PrintWriter pw;
                private Socket socket;
                private Bank bank;
                private volatile boolean running = true;
                private Gson gson;

            /** Constructor **/
            public ClientWrite(Socket socket , Bank bank){
                this.socket = socket;
                this.bank=bank;
            }

            @Override
            public void run(){
                try {
                    pw = new PrintWriter(socket.getOutputStream());
                    gson = new Gson();
                    while(running){
                        Thread.sleep(1);
                        // sends player to server witch adds it a array
                        pw.println(gson.toJson(bank.getPlayer()));
                        pw.flush();
                        // sends bullet to server witch adds it a array
                        pw.println(gson.toJson(bank.getBullet()));
                        pw.flush();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        pw.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void isRunning(boolean running) {
                this.running = running;
            }
    }



