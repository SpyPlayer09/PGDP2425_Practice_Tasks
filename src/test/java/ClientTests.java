import GroupChat.Client;
import GroupChat.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ClientTests {
    private Server server;

    private ByteArrayOutputStream localOut = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        server = new Server(8000);
        server.start();
        System.setOut(new PrintStream(localOut));
    }

    @Test
    public void TestBasicSetup() {
        try {

            String simulatedInput = "Markus\n";
            InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);
            var client = new Client("localhost", 8000);
            client.connect();
            client.close();
            String output = localOut.toString();

            assertTrue(output.contains("Successfully established connection to server, you can now start chatting"), "Success message not as expected");
            assertTrue(output.contains("Please enter your username: "), "Please enter username message not as expected");
            assertTrue(output.contains("Markus connected"), "User connected not as expected");
        } catch (Exception e) {
            fail("Failed to connect to server with exception: " + e);
        }
    }

    @Test
    public void TestWriteToServer() {
        try {
            String simulatedInput = "Markus\nWas geht Leute?\n";
            InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);
            var client = new Client("localhost", 8000);
            client.connect();

            String otherClient = "Peter\nNicht viel, bei dir?";
            InputStream inputStream2 = new ByteArrayInputStream(otherClient.getBytes());
            System.setIn(inputStream2);
            var client2 = new Client("localhost", 8000);
            client2.connect();

            Thread.sleep(1000);
            client.close();
            client2.close();
            String output = localOut.toString();
            assertTrue(output.contains("Successfully established connection to server, you can now start chatting"), "Success message not as expected");
            assertTrue(output.contains("Please enter your username: "), "Please enter username message not as expected");
            assertTrue(output.contains("Markus connected"), "User connected not as expected");
            assertTrue(output.contains("Was geht Leute?"), "Message not as expected");

        } catch (Exception e) {
            fail("Failed to connect to server with exception: " + e);
        }
    }

    @AfterEach
    public void teardown() {
        server.stopServer();
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.setOut(originalOut);
    }

}
