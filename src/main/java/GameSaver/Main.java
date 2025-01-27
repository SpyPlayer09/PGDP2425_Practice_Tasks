package GameSaver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        var consoleReader = new BufferedReader(new InputStreamReader(System.in));
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
