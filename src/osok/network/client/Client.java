package osok.network.client;


import javafx.scene.shape.Circle;

import java.io.IOException;
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
        try(Socket socket = new Socket(ip, port)) {

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
