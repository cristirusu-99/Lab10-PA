package clientLogic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class GameClient {
    public static void runClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "", serverMessage = "";

            // Identification:
            serverMessage = inputStream.readUTF();
            System.out.println(serverMessage);
            clientMessage = reader.readLine();
            outputStream.writeUTF(clientMessage);
            outputStream.flush();
            serverMessage = inputStream.readUTF();
            System.out.println(serverMessage);

            // Requests:
            while (!clientMessage.equals("exit")) {
                System.out.println("Enter request :");
                clientMessage = reader.readLine();
                outputStream.writeUTF(clientMessage);
                outputStream.flush();
                serverMessage = inputStream.readUTF();
                System.out.println(serverMessage);
            }

            // Close message:
            serverMessage = inputStream.readUTF();
            System.out.println(serverMessage);
            outputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        GameClient.runClient();
    }
}
