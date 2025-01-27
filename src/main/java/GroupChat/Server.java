package GroupChat;

import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ConcurrentHashMap;

public class Server extends Thread{
    private final int port;
    private ServerSocket serverSocket;

    private boolean isRunning;

    private final ConcurrentHashMap<Integer, ClientHandler> activeClients = new ConcurrentHashMap<>();
    private int nextClientId = 0;

    public static void main(String[] args) {
        int port = 8000;
        var server = new Server(port);
        System.out.println("Starting server on port " + port);
        server.start();
    }

    public Server(int port) {
        this.port = port;
    }

    ///
    /// Erstelle einen Serversocket auf dem Port, setze dann isRunning auf true.
    /// Akzeptiere eingehende Verbindungen auf dem Serversocket.
    /// Jede neue Verbindung wird durch einen neuen ClientHandler verarbeitet, der in activeClients gespeichert wird.
    /// FÜR TESTS WICHTIG: Füge nach dem Erstellen des ServerSockets diese Zeile ein: serverSocket.setSoTimeout(1000);
    ///
    public void run() {
        //todo erstelle den Serversocket

        while (isRunning){
            try {
                var clientSocket = serverSocket.accept();
                //todo verarbeite die eingehende Verbindung
            } catch (SocketTimeoutException e) {
                // Dieser Catch Block ist nur für den Socket Timeout relevant, du musst hier keinen eigenen Code schreiben
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        shutDown();
    }

    ///
    /// Iteriere über alle Clients und schreibe die Nachricht an alle außer den Client mit der übergebenen ID
    ///
    public void writeToAllClients(int clientId, String message) {
        //todo
    }

    public void removeClient(int clientId) {
        activeClients.remove(clientId);
    }

    ///
    /// Iteriere über alle ClientHandler und schließe sie, schließe anschließend auch den ServerSocket
    /// Beachte, dass close bei ClientHandler auch auf activeClients zugreift
    ///
    private void shutDown() {
        //todo
    }

    public void stopServer() {
        isRunning = false;
    }

}
