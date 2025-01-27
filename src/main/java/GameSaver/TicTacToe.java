package GameSaver;

import java.io.IOException;

import Helpers.ConsoleHelper;

public class TicTacToe {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }

    public enum Piece {
        EMPTY, X, O
    }

    private Piece[][] board;
    private boolean isXTurn = true;
    private Piece winner = Piece.EMPTY;

    public TicTacToe() {
        board = new Piece[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    public TicTacToe(Piece[][] board, boolean isXTurn) {
        this.board = board;
        this.isXTurn = isXTurn;
        checkForWinner();
    }

    public void playGame() {
        while (!checkForWinner()) {
            printBoard();
            boolean turn = makeTurn(isXTurn ? Piece.X : Piece.O);
            if (!turn) return;
        }
        printBoard();
        System.out.println("Player " + winner + " wins!");
    }

    private boolean makeTurn(Piece piece) {
        String player = piece == Piece.X ? "Player X:" : "Player O:";
        while (true) {
            try {
                System.out.print(player + " Enter x and y coordinates <x y>: ");
                String[] input = ConsoleHelper.readLine().split(" ");
                if (input[0].equals("exit")){
                    return false;
                }
                if (input[0].equals("save")) {
                    GameSaver.SaveGame(this, input[1]);
                    return false;
                }
                int x = Integer.parseInt(input[0]) - 1;
                int y = Integer.parseInt(input[1]) - 1;
                if (board[x][y] == Piece.EMPTY) {
                    board[x][y] = piece;
                } else {
                    System.out.println("Field already taken");
                    continue;
                }
                isXTurn = !isXTurn;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        return true;
    }

    public boolean checkForWinner() {
        //Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Piece.EMPTY) {
                winner = board[i][0];
                return true;
            }
        }
        //Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Piece.EMPTY) {
                winner = board[0][i];
                return true;
            }
        }
        //Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Piece.EMPTY) {
            winner = board[0][0];
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != Piece.EMPTY) {
            winner = board[0][2];
            return true;
        }
        return false;
    }

    public void printBoard() {
        System.out.println("---------\n\\ 1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X:
                        System.out.print("X");
                        break;
                    case O:
                        System.out.print("O");
                        break;
                    case EMPTY:
                        System.out.print(" ");
                        break;
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------\n");
    }

    public void setPiece(Piece p, int x, int y) {
        board[x][y] = p;
    }

    public void setTurn(boolean isXTurn) {
        this.isXTurn = isXTurn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String ch = convertToString(board[i][j]);
                sb.append(ch).append(" ");
            }
            sb.append("\n");
        }
        sb.append(isXTurn).append("\n");
        sb.append(winner).append("\n");
        return sb.toString();
    }

    public static String convertToString(Piece p) {
        return p == Piece.EMPTY ? "E" : p == Piece.X ? "X" : "O";
    }

    public static Piece convertToPiece(String s) {
        return s.equals("E") ? Piece.EMPTY : s.equals("X") ? Piece.X : Piece.O;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public boolean getIsXTurn() {
        return isXTurn;
    }

    public Piece getWinner() {
        return winner;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }
}
