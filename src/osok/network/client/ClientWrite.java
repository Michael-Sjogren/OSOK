package osok.network.client;

import java.net.Socket;

/**
 * Created by Michael Sjögren on 2016-05-20.
 */
public class ClientWrite implements Runnable {

    private Socket socket = null;
    private boolean running = true;


    public ClientWrite(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try {
            while(running){
                Thread.sleep(1500);
                System.out.println("write");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void isRunning(boolean running) {
        this.running = running;
    }
}
