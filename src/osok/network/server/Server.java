package osok.network.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private static Socket csocket;

    Server(Socket csocket) {
        this.csocket = csocket;
    }

    public static void main(String args[]) {

        try(ServerSocket ssock = new ServerSocket(55555)) {
            System.out.println("isServerSocket closed : " + ssock.isClosed());
            while(true){
                csocket = ssock.accept();
                System.out.println("client connected" + "");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void run() {
        try(ObjectOutputStream pstream = new ObjectOutputStream(csocket.getOutputStream())) {

            for (int i = 100; i >= 0; i--) {
                pstream.writeUTF(i +
                        " bottles of beer on the wall");
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
