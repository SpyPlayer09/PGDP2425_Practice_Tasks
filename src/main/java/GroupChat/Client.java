package GroupChat;

import Helpers.ConsoleReader;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String username;
    private String serverAddress;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private Thread readerThread;
    private Thread writerThread;

    private final ConsoleReader consoleReader;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
        consoleReader = new ConsoleReader();
    }


    public static void main(String[] args) {
        var client = new Client("localhost", 8000);
        client.connect();
    }

    ///
    /// Bitte den Nutzer zur Eingabe seines Benutzernamens mit "Please enter your username: ".
    /// Erstelle danach einen Socket zum Server und erstelle die Reader und Writer auf diesen Streams.
    /// Sende jetzt direkt den Nutzernamen an den Server und starte dann noch die jeweiligen Reader und Writer Threads.
    /// Am Ende gebe "Successfully established connection to server, you can now start chatting" auf der Konsole aus.
    ///
    public void connect() {
        //todo
    }

    ///
    /// Lies kontinuierlich, wenn vorhanden, Nachrichten von der Konsole aus und schicke diese an den Server.
    /// Sollten keine Nachrichten vorhanden sein, lasse den Thread 100ms warten.
    /// Sollte der Thread unterbrochen werden, beende die Methode
    ///
    private void WriterThread() {
        //todo
    }

    ///
    /// Lies kontinuierlich, falls vorhanden, Nachrichten vom Server und gib diese auf der Konsole aus.
    /// Sollten keine Nachrichten vorhanden sein, lasse den Thread 100ms warten.
    /// Sollte der Thread unterbrochen werden, beende die Methode
    ///
    public void ReaderThread() {
        //todo
    }

    ///
    /// Unterbreche beide Threads, warte auf deren Beendigung und schließe den Socket
    ///
    public void close() {
        //todo
    }
}
