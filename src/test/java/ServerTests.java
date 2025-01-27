import FizzBuzzStream.FizzBuzz;
import GroupChat.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTests {
    private Server server;

    @BeforeEach
    public void setup() {
        server = new Server(8000);
        server.start();
    }

    @Test
    public void TestBasicSetup() {
        try {
            Socket clientSocket = new Socket("localhost", 8000);
            clientSocket.close();
            server.stopServer();
            server.join();
        } catch (Exception e) {
            fail("Failed to connect to server with exception: " + e);
        }
    }

    @Test
    public void TestWriteToAllClients() {
        try {
            Socket clientSocket1 = new Socket("localhost", 8000);
            Socket clientSocket2 = new Socket("localhost", 8000);
            Socket clientSocket3 = new Socket("localhost", 8000);
            // Write a message in the first socket and read from the next two
            var writer1 = new PrintWriter(clientSocket1.getOutputStream(), true);
            var writer2 = new PrintWriter(clientSocket2.getOutputStream(), true);
            var writer3 = new PrintWriter(clientSocket3.getOutputStream(), true);
            writer1.println("Markus");
            writer2.println("Lukas");
            writer3.println("Johannes");

            writer1.println("Hello World");
            var reader2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
            var reader3 = new BufferedReader(new InputStreamReader(clientSocket3.getInputStream()));
            assertEquals("Markus: Hello World", reader2.readLine());
            assertEquals("Markus: Hello World", reader3.readLine());
            //check that the first client has no data
            var reader1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
            assertFalse(reader1.ready());
            clientSocket1.close();
            clientSocket2.close();
            clientSocket3.close();
        } catch (Exception e) {
            fail("Encountered an unexpected exception during the test: " + e);
        }
    }

    @AfterEach
    public void tearDown() {
        server.stopServer();
        try {
            server.join();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            fail("Failed to stop server");
        }
    }
}
