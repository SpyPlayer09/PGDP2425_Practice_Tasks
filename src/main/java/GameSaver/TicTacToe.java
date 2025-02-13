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
        //todo
    }

    ///
    /// Erstelle das Spielbrett und Initialisiere es mit den gegebenen Werten
    /// Überprüfe auch, ob es bereits einen Gewinner gibt
    ///
    public TicTacToe(Piece[][] board, boolean isXTurn) {
        //todo
    }

    ///
    /// Drucke das Board aus und lass den richtigen Spieler seinen Zug machen, wiederhole das so lange, bis ein Zug
    /// entweder das Spiel abbricht oder ein Spieler gewonnen hat
    /// Drucke dann das finale Board aus und gebe den Gewinner aus
    ///
    public void playGame() {
        //todo
    }


    ///
    /// Frage den Spieler nach den SpielKoordinaten im Format <x y> und setze das entsprechende Feld auf das Symbol des Spielers
    /// Sollte der Input "exit" sein, beende das Spiel ohne weiteres
    /// Sollte der Input "save <filename>" sein, speichere das Spiel and der Stelle und beende das Spiel dann.
    /// Sollte der Input ungültig sein, gib "Invalid input" aus und frage erneut nach den Koordinaten.
    /// Sollte das Feld bereits belegt sein, gib "Field already taken" aus und frage erneut nach den Koordinaten.
    /// Nachdem der Zug gemacht wurde, wechsle den Spieler.
    ///
    private boolean makeTurn(Piece piece) {
        //todo
        return false;
    }

    ///
    /// Überprüfe alle möglichen Gewinnkombinationen und setze den Gewinner, falls es einen gibt.
    /// Die Methode gibt true zurück, falls es einen Gewinner gibt, ansonsten false
    ///
    public boolean checkForWinner() {
        //todo
        return false;
    }

    ///
    /// Konvertiere das Spielbrett und den aktuellen Zustand in eine sinnvolle String repräsentation,
    /// aus der das Spiel wiederhergestellt werden kann.
    ///
    @Override
    public String toString() {
        //todo
        return "";
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
