package ServerClient;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
/*
   Job: Ensures correctness of behaviour server and clientOne
*/

public class ServerTest {

    public static String serverName = "localhost";
    public static int portNumber = 1234;
    Server server;
    Client clientOne;
    Client clientTwo;

    @Test
    public void connecting_one_client_to_server_of_localHost_gets_connected_message() throws IOException {
        server = Server.createServer(1);
        clientOne = Client.createClient(serverName, portNumber, "Abhilash");

        server.startEvents();
        String answer = clientOne.getServerMessage();

        Assert.assertEquals("Connected", answer);

        clientOne.close();
        server.close();
    }

    @Test
    public void connecting_two_client_to_server_of_localHost_gets_connected_message() throws IOException {
        server = Server.createServer(2);
        clientOne = Client.createClient(serverName, portNumber,"Abhilash");
        clientTwo = Client.createClient(serverName, portNumber,"Abhilash");

        server.startEvents();
        String answer = clientOne.getServerMessage();
        String answerTwo = clientTwo.getServerMessage();

        Assert.assertEquals("Connected", answer);
        Assert.assertEquals("Connected", answerTwo);

        clientOne.close();
        clientTwo.close();
        server.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void requesting_for_a_connection_with_zero_clients_to_the_localHost_throws_IllegalArgumentException() throws IOException {
        server = Server.createServer(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void requesting_for_a_connection_with_negative_number_of_clients_to_the_localHost_throws_IllegalArgumentException() throws IOException {
        server = Server.createServer(-2);
    }


}

