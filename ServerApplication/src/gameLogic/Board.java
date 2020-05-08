package gameLogic;

import javafx.util.Pair;

import java.util.Arrays;

public class Board {
    private static int W = 15;
    private static int H = 15;
    private static int WhitePiece = 1;
    private static int BlackPiece = 2;

    private int[][] board;

    public Board() {
        this.board = new int[W][H];
        for (int i = 0; i<H; ++i)
            for (int j = 0; j<W; ++j)
                board[i][j] = 0;
    }

    public boolean putPiece(int x, int y, int playerColor) {
        if (!((1 <= x && x <= W) && (1 <= y && y <= H))) // NOT ON  BOARD
            return false;
        if (board[x - 1][y - 1] != 0) return false; // OCCUPIED
        board[x - 1][y - 1] = playerColor;
        return true;
    }

    public Pair<Boolean, Integer> checkLineOf5() {
        // VERTICAL CHECK
        boolean found = false;
        for (int i = 0; i <= W - 5; i++)
            for (int j = 0; j < H; j++) {
                if (board[i][j] != 0) { // AVOID FREE SPOTS
                    found = true;
                    for (int k = 1; k < 5; k++) {
                        if (board[i][j] != board[i + k][j]) {
                            found = false;
                            break;
                        }
                    }
                    if (found) return new Pair(true, board[i][j]);
                }
            }
        // HORIZONTAL CHECK
        for (int i = 0; i < W; i++)
            for (int j = 0; j <= H - 5; j++) {
                if (board[i][j] != 0) { // AVOID FREE SPOTS
                    found = true;
                    for (int k = 1; k < 5; k++) {
                        if (board[i][j] != board[i][j + k]) {
                            found = false;
                            break;
                        }
                    }
                    if (found) return new Pair(true, board[i][j]);
                }
            }

        // DIAGONAL CHECK \
        for (int i = 0; i <= W - 5; i++) {
            for (int j = 0; j <= H - 5; j++) {
                if (board[i][j] != 0) { // AVOID FREE SPOTS
                    found = true;
                    for (int k = 1; k < 5; k++) {
                        if (board[i][j] != board[i + k][j + k]) {
                            found = false;
                            break;
                        }
                    }
                    if (found) return new Pair(true, board[i][j]);
                }
            }
        }
        // DIAGONAL CHECK \
        for (int i = 4; i < W; i++) {
            for (int j = 0; j <= H - 5; j++) {
                if (board[i][j] != 0) { // AVOID FREE SPOTS
                    found = true;
                    for (int k = 1; k < 5; k++) {
                        if (board[i][j] != board[i - k][j + k]) {
                            found = false;
                            break;
                        }
                    }
                    if (found) return new Pair(true, board[i][j]);
                }
            }
        }
        return new Pair(false, 0);
    }

    public String getPrintableBoard() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i<H; ++i){
            for (int j = 0; j<W; ++j){
                boardString.append(this.board[i][j]).append(" ");
            }
            boardString.append("\n");
        }
        return  boardString.toString();
    }
}
