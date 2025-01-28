package GameSaver;

import Helpers.ConsoleReader;

public class TicTacToe {
    private Piece[][] board;
    private boolean isXTurn = true;
    private Piece winner = Piece.EMPTY;
    private ConsoleReader consoleReader;

    public enum Piece {
        EMPTY, X, O
    }
    ///
    /// Erstelle das Spielbrett und Initialisiere es mit leeren Feldern#
    ///
    public TicTacToe() {
        consoleReader = new ConsoleReader();
        board = new Piece[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    ///
    /// Erstelle das Spielbrett und Initialisiere es mit den gegebenen Werten
    /// Überprüfe auch, ob es bereits einen Gewinner gibt
    ///
    public TicTacToe(Piece[][] board, boolean isXTurn) {
        this.board = board;
        this.isXTurn = isXTurn;
        checkForWinner();
    }

    ///
    /// Drucke das Board aus und lass den richtigen Spieler seinen Zug machen, wiederhole das solange, bis ein Zug
    /// entweder das Spiel abbricht oder ein Spieler gewonnen hat
    /// Drucke dann das finale Board aus und gebe den Gewinner aus
    ///
    public void playGame() {
        while (!checkForWinner()) {
            printBoard();
            boolean turn = makeTurn(isXTurn ? Piece.X : Piece.O);
            if (!turn) return;
        }
        printBoard();
        System.out.println("Player " + winner + " wins!");
    }


    ///
    /// Frage den Spieler nach den SpielKoordinaten im Format <x y> und setze das entsprechende Feld auf das Symbol des Spielers
    /// Sollte der Input "exit" sein, beende das Spiel ohne weiteres
    /// Sollte der Input "save <filename>" sein, speichere das Spiel und beende das Spiel dann.
    /// Sollte der Input ungültig sein, gib "Invalid input" aus und frage erneut nach den Koordinaten.
    /// Sollte das Feld bereits belegt sein, gib "Field already taken" aus und frage erneut nach den Koordinaten.
    /// Nachdem der Zug gemacht wurde, wechsle den Spieler.
    ///
    private boolean makeTurn(Piece piece) {
        String player = piece == Piece.X ? "Player X:" : "Player O:";
        while (true) {
            try {
                System.out.print(player + " Enter x and y coordinates <x y>: ");
                String[] input = consoleReader.readLine().split(" ");
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

    ///
    /// Überprüfe alle möglichen Gewinnkombinationen und setze den Gewinner, falls es einen gibt.
    /// Die Methode gibt true zurück, falls es einen Gewinner gibt, ansonsten false
    ///
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

    ///
    /// Konvertiere das Spielbrett und den aktuellen Zustand in eine sinnvolle String repräsentation,
    /// aus der das Spiel wiederhergestellt werden kann.
    ///
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

    ///
    /// Hilfsmethoden
    ///
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
