package GameSaver;

import Helpers.ConsoleReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    ///
    /// Hier kannst du eine komplette TicTacToe-Partie Implementieren.
    /// Frage den Nutzer zuerst, ob er ein neues Spiel starten oder ein altes Spiel laden möchte.
    /// Sollte der Nutzer ein neues Spiel starten wollen, erstelle ein neues TicTacToe-Objekt und starte das Spiel.
    /// Sollte der Nutzer ein altes Spiel laden wollen, lade das Spiel und starte es.
    /// Deine genaue Implementierung hier wird nicht überprüft, also kannst du sie nach Belieben anpassen
    ///
    public static void main(String[] args) {
        var consoleReader = new ConsoleReader();
        System.out.println("Please enter if you would like to \"start\" a new game or \"load <filename>\" an existing game: ");
        try {
            while (true){
                String input = consoleReader.readLine();
                if (input.equals("start")) {
                    var game = new TicTacToe();
                    game.playGame();
                    break;
                } else if (input.startsWith("load")) {
                    String[] parts = input.split(" ");
                    var game = GameSaver.LoadGame(parts[1]);
                    game.playGame();
                    break;
                }
                else {
                    System.out.println("Invalid input, please try again");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
