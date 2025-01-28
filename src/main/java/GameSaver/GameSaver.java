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
        //todo
    }

    ///
    /// Lade ein Spiel von einer Datei mit dem Namen filePath an dem Pfad savePath
    /// Exceptions müssen nicht besonders behandelt werden
    ///
    public static TicTacToe LoadGame(String filePath){
        //todo
        return null;
    }
}
