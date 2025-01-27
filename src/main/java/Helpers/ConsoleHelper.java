package Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            return consoleReader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ready() {
        try {
            return consoleReader.ready();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
