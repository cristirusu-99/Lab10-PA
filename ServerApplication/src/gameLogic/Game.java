package gameLogic;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentTurn;


    public Game(Board board) {
        this.board = board;
        players = new ArrayList<>();
        currentTurn = 1;
    }

    public boolean joinGame(Player player) {
        if(players.size() == 2) return false;
        if(players.contains(player)) return false;
        players.add(player);
        player.setTurnNo(players.size());
        return true;
    }

    public boolean makeMove(int x, int y) {
        return board.putPiece(x, y, currentTurn);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
}
