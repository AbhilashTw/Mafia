import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
/*
   Job: Ensures correctness of behaviour server and clientOne
*/

public class ServerTest {

    Server server;
    Client clientOne;
    Client clientTwo;

    @Before
    public void setUp() throws IOException {
        server = new Server();
        clientOne = new Client();
        clientTwo = new Client();
    }

    @After
    public void tearDown() throws IOException {
        server.close();
        clientOne.close();
        clientTwo.close();
    }

    @Test
    public void connecting_one_client_to_server_of_localHost_gets_connected_message() throws IOException {
        server.start();
        server.sendMessage();
        String answer = clientOne.getServerMessage();
        Assert.assertEquals("Connected", answer);

    }
}

