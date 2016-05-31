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
public class Client{
    private ClientRead read;
    private ClientWrite write;
    private Thread writingThread;
    private Thread readingThread;
    private Socket socket;
    private ChatClient chatClient;
    private Bank bank;
    private Player player;



    /* konstruktor */
    public Client(Bank bank, ChatClient chatClient){


        this.bank = bank;
        this.player = bank.getPlayer();

        try {
           Socket socket = new Socket( bank.getPlayer().getIp() ,bank.getPlayer().getPort());

            if(socket.isClosed()){
                return;
            }else{
                player.setIsConnected(true);
            }
           this.socket = socket;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" -- Client connected -- ");
        /* start write thread for client */
        write = new ClientWrite(socket , bank );
        writingThread = new Thread(write , " client-write-thread ");
        writingThread.start();

            /* start read thread for client */
        read = new ClientRead(socket,bank);
        readingThread = new Thread(read , "  client-read-thread ");
        readingThread.start();

    }

    /** terminates threads **/
    public void shutdown()  {
            read.isRunning(false);
            write.isRunning(false);
            try {
                    socket.close();
                    readingThread.join();
                    writingThread.join();
                    System.out.println("-- client all threads terminated --");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

    /** reads from server **/
    class ClientRead implements Runnable{

            private BufferedReader br;
            private Socket socket;
            private Bank bank;
            private volatile boolean running = true;
            private  ArrayList<String> oldPlayers;
            private ArrayList<Player> newPlayers = new ArrayList<>();
            private ArrayList<String> oldBullets;
            private ArrayList<Bullet> newBullets = new ArrayList<>();


        public ClientRead(Socket socket, Bank bank){
                this.socket = socket;
                this.bank=bank;
                }

        @Override
        public void run(){
                    System.out.println(socket.isConnected());
               //      String condition = bank.getPlayer().getUsername() + " : " + "null";

                    try  {
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        Gson gson = new Gson();

                         while(running){
                             Thread.sleep(1);
                             oldPlayers = gson.fromJson( br.readLine() , ArrayList.class);
                             oldBullets = gson.fromJson( br.readLine() , ArrayList.class);
                             newBullets.clear();
                             newPlayers.clear();
                             try{
                                 for(int i = 0; i < oldPlayers.size(); i++){
                                     newPlayers.add(gson.fromJson(oldPlayers.get(i), Player.class));
                                    }
                                 for(int i = 0; i < oldBullets.size(); i++){
                                     newBullets.add(gson.fromJson(oldBullets.get(i), Bullet.class));
                                 }

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
                        pw.println(gson.toJson(bank.getPlayer()));
                        pw.flush();
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



