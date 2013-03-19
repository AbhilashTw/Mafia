import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
//Job:- Ensures correctness of behaviour of client
public class ClientTest {
    Server server;
    Client client;

    @Before
    public void setUp() throws Exception {
        server = new Server();
        client = new Client();
    }

    @After
    public void tearDown() throws Exception {
        server.close();
        client.close();
    }

    @Test
    public void connect_to_server_in_the_localHost_and_get_a_connected_message() throws IOException {
        server.runServer();
        Assert.assertEquals("Connected",client.getServerMessage());
    }
}
