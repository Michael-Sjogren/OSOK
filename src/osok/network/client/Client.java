package osok.network.client;


import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Michael Sjögren on 2016-05-20.
 */
public class Client implements Runnable {
    private  int port;
    private  String ip;
    private ClientRead read;
    private ClientWrite write;
    private Thread writingThread;
    private Thread readingThread;




    /* konstruktor */
    public Client(String ip, int port){
    this.ip = ip;
    this.port = port;
    }

    @Override
    public void run() {
        System.out.println("client thread running");
        try(Socket socket = new Socket("127.0.0.1", 55555)) {

            System.out.println("Client connected");

            /* start read thread for client */
            read = new ClientRead(socket);
            readingThread = new Thread(read);
            readingThread.start();

             /* start write thread for client */
            write = new ClientWrite(socket);
            writingThread = new Thread(write);
            writingThread.start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setCirclePos(double centerX, double centerY) {
    }

    public void shutdown()  {
        try {
            read.isRunning(false);
            write.isRunning(false);

            writingThread.join();
            readingThread.join();
            System.out.println("threads terminated");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/* reads from server */
class ClientRead implements Runnable{

    private Socket socket;
    private boolean running = true;

    public ClientRead(Socket socket){
            this.socket = socket;
            }

    @Override
    public void run(){
            System.out.println(socket.isConnected());
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            while(true){
            Thread.sleep(2000);
                System.out.println("reads server");
            }
            }catch (Exception e){
            e.printStackTrace();
            }
    }

            public void isRunning(boolean running) {
            this.running = running;
            }
 }
/* writes to server */
class ClientWrite implements Runnable{
    private Socket socket;
    private boolean running = true;

    public ClientWrite(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        System.out.println(socket.isConnected());
        try {
            ObjectOutputStream in = new ObjectOutputStream(socket.getOutputStream());
            while(running){
                Thread.sleep(2000);
                in.writeUTF("hello");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void isRunning(boolean running) {
        this.running = running;
    }
}
