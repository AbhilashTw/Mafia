package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import entities.ClientPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.client.JoinServerView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Job:- Ensures the correctness of behaviour of JoinServerController.
 */
public class JoinServerControllerTest {
    Workflow workflow;
    JoinServerView joinServerView;
    SocketChannel socketChannel;
    JoinServerController joinServerController;
    ClientPlayer clientPlayer;

    @Before
    public void setUp() throws Exception {
        clientPlayer = mock(ClientPlayer.class);
        workflow = mock(Workflow.class);
        joinServerView = mock(JoinServerView.class);
        socketChannel = mock(SocketChannel.class);
        joinServerController = new JoinServerController(workflow, clientPlayer);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_invoke_connectionToServerFailed_from_view_when_onConnectionFailed_is_called_from_joinServerController() {
        Exception e = mock(Exception.class);
        joinServerController.bind(joinServerView);
        joinServerController.onConnectionFailed("localhost", 1234, e);
        verify(joinServerView).connectionToServerFailed();
    }

}
