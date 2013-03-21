package ServerClient;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

//Job:- Ensures correctness of behaviour of clientOne

public class ClientTest {
    public static String serverName = "localhost";
    public static int portNumber = 1234;
    Server server;
    Client client;

    @Before
    public void setUp() throws Exception {
        server = Server.createServer(1);
        client = Client.createClient(serverName, portNumber, "Abhilash");
    }

    @After
    public void tearDown() throws Exception {
        server.close();
        client.close();
    }

    @Test
    public void connect_to_server_in_the_localHost_and_get_a_connected_message() throws IOException {
        server.startEvents();
        Assert.assertEquals("Connected", client.getServerMessage());
    }
}
