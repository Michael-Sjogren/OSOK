package osok.network.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by Michael Sjögren on 2016-05-20.
 */
public class ClientRead implements Runnable {
    private Socket socket;
    private boolean running = true;

    public ClientRead(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        System.out.println(socket.isConnected());
        try {
            Thread.sleep(1000);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while(running){
              Thread.sleep(2000);

                System.out.println(in.readUTF());

            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e1){
            e1.printStackTrace();
        }
    }

    public void isRunning(boolean running) {
        this.running = running;
    }
}
