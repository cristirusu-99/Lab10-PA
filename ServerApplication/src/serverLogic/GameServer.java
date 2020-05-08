package serverLogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static void runServer() throws IOException {
        try {
            ServerSocket server = new ServerSocket(5000);
            int clientNumber = 0;
            System.out.println("Server Started ....");
            while (true) {
                clientNumber++;
                Socket serverClient = server.accept();  //server accept the client connection request

                System.out.println(" [Server] " + "Client with No:" + clientNumber + "  just connected!");
                ClientThread clientThread = new ClientThread(serverClient, clientNumber); //send  the request to a separate thread
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        GameServer.runServer();
    }
}
