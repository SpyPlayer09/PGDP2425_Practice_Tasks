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
    /// Versuche einen Serversocket auf dem Port zu erstellen, setze dann isRunning auf true.
    /// Warte dann in einer Schleife, solange der Server aktiv läuft und akzeptiere eingehende Verbindungen auf dem Serversocket.
    /// Jede neue Verbindung wird durch einen neuen ClientHandler verarbeitet, der in activeClients gespeichert wird.
    /// FÜR TESTS WICHTIG: Füge nach dem Erstellen des ServerSockets diese Zeile ein: serverSocket.setSoTimeout(1000);
    ///
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            isRunning = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (isRunning) {
            try {
                var clientSocket = serverSocket.accept();
                var clientHandler = new ClientHandler(nextClientId, clientSocket, this);
                activeClients.put(nextClientId++, clientHandler);
                clientHandler.start();
            } catch (SocketTimeoutException e) {
                // Dieser Catch Block ist nur für den Socket Timeout relevant, du musst hier keinen eigenen Code schreiben, platziere
                //deine eigenen Catch-Anweisungen aber nach diesem Block
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
        for (var i : activeClients.keySet()) {
            if (i != clientId) {
                activeClients.get(i).WriteToClient(message);
            }
        }
    }

    public void removeClient(int clientId) {
        activeClients.remove(clientId);
    }

    ///
    /// Iteriere über alle Clients und schließe sie, schließe anschließend auch den ServerSocket
    ///
    private void shutDown() {
        try {
            for (var client : activeClients.values()) {
                client.close();
            }
            serverSocket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stopServer() {
        isRunning = false;
    }

}
