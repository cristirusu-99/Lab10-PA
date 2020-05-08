package serverLogic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    Socket client;
    DataInputStream inStream = null;
    DataOutputStream outStream = null;
    int clientNo;
    String clientName;

    ClientThread(Socket inSocket, int clientNo) throws IOException{
        this.client = inSocket;
        this.clientNo = clientNo;
        inStream = new DataInputStream(client.getInputStream());
        outStream = new DataOutputStream(client.getOutputStream());
    }

    public void run() {
        try {
            String clientMessage = "", serverMessage = "";

            // Identification:
            serverMessage = "Hello new client! Please input a name!";
            outStream.writeUTF(serverMessage);
            outStream.flush();
            clientName = inStream.readUTF();
            serverMessage = "From Server: Welcome " + clientName + ", your client number is: " + clientNo + ".";
            outStream.writeUTF(serverMessage);
            outStream.flush();
            System.out.println("Thread_" + this.getId() + ": Client-" + clientNo + " has identified with Name:" + clientName + "!");

            // Requests:
            while (!clientMessage.equals("exit")) {
                clientMessage = inStream.readUTF();
                System.out.println("Thread_" + this.getId() + ": Received from Client-" + clientName + ": Request is :"
                        + clientMessage);
                serverMessage = "From Server(by Thread_" + this.getId() + ") to Client-" + clientName + ": Request of <" +
                        clientMessage + "> has been processed!";
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }

            // Close message
            serverMessage = "From Server(by Thread_" + this.getId() + ") to Client-" + clientName + ": You have been disconnected!";
            outStream.writeUTF(serverMessage);
            outStream.flush();
            inStream.close();
            outStream.close();
            client.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Thread_" + this.getId() + ": Client with No:" + clientNo + " and Name: " + clientName + " disconnected!");
        }
    }
}
