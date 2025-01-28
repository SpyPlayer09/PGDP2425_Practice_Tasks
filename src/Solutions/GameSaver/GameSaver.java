package GameSaver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameSaver {
    public static String savePath = "./src/main/java/GameSaver/";

    ///
    /// Speichere das Spiel in einer Datei mit dem Namen saveName an dem Pfad savePath
    /// Exceptions müssen nicht besonders behandelt werden
    ///
    public static void SaveGame(TicTacToe toSave, String saveName) {
        try {
            Files.writeString(Path.of(savePath + saveName), toSave.toString());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    ///
    /// Lade ein Spiel von einer Datei mit dem Namen filePath an dem Pfad savePath
    /// Exceptions müssen nicht besonders behandelt werden
    ///
    public static TicTacToe LoadGame(String filePath){
        TicTacToe result;
        try {
            TicTacToe.Piece[][] board = new TicTacToe.Piece[3][3];
            var lines = Files.readAllLines(Path.of(savePath + filePath));
            for (int i = 0; i < 3; i++) {
                var pieces = lines.get(i).split(" ");
                for (int j = 0; j < 3; j++) {
                    board[i][j] = TicTacToe.convertToPiece(pieces[j]);
                }
            }
            result = new TicTacToe(board, Boolean.parseBoolean(lines.get(3)));
        } catch (Exception e){
            System.out.println("Something went wrong reading the file");
            throw new RuntimeException(e);
        }
        return result;
    }
}
