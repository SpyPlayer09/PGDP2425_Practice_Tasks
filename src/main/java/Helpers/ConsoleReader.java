package Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleReader {
    private BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));


    ///
    /// Liest eine Zeile von der Konsole
    ///
    public String readLine() {
        try {
            return consoleReader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ///
    /// Gibt zurück, ob Daten auf der Konsole bereit zum Lesen sind
    ///
    public boolean ready() {
        try {
            return consoleReader.ready();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
