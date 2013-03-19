import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
/*
   Job: Ensures correctness of behaviour server and client
*/

public class ServerTest {

    Server server;
    Client client;

    @Before
    public void setUp() throws IOException {
        server = new Server();
        client = new Client();
    }

    @After
    public void tearDown() throws IOException {
        server.close();
        client.close();
    }

    @Test
    public void connecting_one_client_to_server_of_localHost_gets_connected_message() throws IOException {
            server.runServer();
        String answer = client.getServerMessage();
        Assert.assertEquals("Connected", answer);

    }

//    @Test
//    public void connecting_two_clients_to_the_server_on_the_localHost_gets_connected_message() throws IOException {
//        Socket clientOne = new Socket("localhost", 1234);
//        Socket clientTwo = new Socket("localhost", 1234);
//
//        server.runServer();
//
//
//        String answerOne = getServerMessage(clientOne);
//        String answerTwo = getServerMessage(clientTwo);
//
//        Assert.assertEquals("Connected", answerOne);
//        Assert.assertEquals("Connected", answerTwo);
//
//    }
}

