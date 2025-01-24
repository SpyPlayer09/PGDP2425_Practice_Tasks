package GroupChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
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
        try {
            System.out.println("Please enter your username: ");
            String username = new BufferedReader(new InputStreamReader(System.in)).readLine();
            socket = new Socket(serverAddress, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(username);
            readerThread = new Thread(this::ReaderThread);
            readerThread.start();
            writerThread = new Thread(this::WriterThread);
            writerThread.start();
            System.out.println("Successfully established connection to server, you can now start chatting");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ///
    /// Lies kontinuierlich, wenn vorhanden, Nachrichten von der Konsole aus und schicke diese an den Server.
    /// Sollten keine Nachrichten vorhanden sein, lasse den Thread 100ms warten.
    /// Sollte der Thread unterbrochen werden, beende die Methode
    ///
    private void WriterThread() {
        var consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                if (!consoleReader.ready()) {
                    Thread.sleep(100);
                    continue;
                }
                var message = consoleReader.readLine();
                writer.println(message);
            }
        } catch (InterruptedException e) {
            //Normal closing of the Thread
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ///
    /// Lies kontinuierlich, falls vorhanden, Nachrichten vom Server und gib diese auf der Konsole aus.
    /// Sollten keine Nachrichten vorhanden sein, lasse den Thread 100ms warten.
    /// Sollte der Thread unterbrochen werden, beende die Methode
    ///
    public void ReaderThread() {
        try {
            while (true) {
                if (!reader.ready()) {
                    Thread.sleep(100);
                    continue;
                }
                var message = reader.readLine();
                System.out.println(message);
            }
        } catch (InterruptedException e) {
            //Orderly closing of Thread
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///
    /// Unterbreche beide Threads, warte auf deren Beendigung und schließe den Socket
    ///
    public void close() {
        try {
            readerThread.interrupt();
            writerThread.interrupt();
            readerThread.join();
            writerThread.join();
            socket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
