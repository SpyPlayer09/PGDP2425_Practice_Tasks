package GroupChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket clientSocket;
    private String username;
    private int clientId;
    private BufferedReader reader;
    private PrintWriter writer;
    private Server server;

    public ClientHandler(int clientId, Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        try {
            //todo speichere die übergebenen Parameter in den entsprechenden Variablen
            this.server = server;
            this.clientId = clientId;
            //todo erstelle den Reader und Writer auf den Streams des Sockets
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            //todo Lese den Benutzernamen des Clients und gib folgendes auf der Konsole aus "<username> connected"
            username = reader.readLine();
            System.out.println(username + " connected");
        } catch (IOException e) {
            System.out.println("Error creating Client");
            server.removeClient(clientId);
        }
    }

    ///
    /// Lies in einer Endlosschleife Nachrichten aus dem Socket. Schreibe jede Nachricht an alle anderen Clients.
    /// Sollte eine Exception auftreten beende die Methode und schließe den Handler mit der close() Methode
    ///
    public void run() {
        try {
            while (true) {
                var message = reader.readLine();
                server.writeToAllClients(clientId, username + ": " + message);
            }
        } catch (IOException e) {
            System.out.println("Error reading from client " + username);
        } finally {
            System.out.println("Client " + username + " disconnected");
            close();
        }
    }

    public void WriteToClient(String message) {
        writer.println(message);
    }

    ///
    /// Schließe den Socket des Clients und entferne den Client aus dem Server
    ///
    public void close() {
        try {
            clientSocket.close();
            server.removeClient(clientId);
        } catch (IOException e) {
            System.out.println("Error closing client socket");
        }
    }


}
