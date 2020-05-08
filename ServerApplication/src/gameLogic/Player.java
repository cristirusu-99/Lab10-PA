package gameLogic;

import java.net.Socket;

public class Player {
    private Socket socket;
    private String name;
    private int turnNo;

    public Player(Socket socket,String name, int turnNo) {
        this.socket = socket;
        this.name = name;
        this.turnNo = turnNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurnNo() {
        return turnNo;
    }

    public void setTurnNo(int turnNo) {
        this.turnNo = turnNo;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
