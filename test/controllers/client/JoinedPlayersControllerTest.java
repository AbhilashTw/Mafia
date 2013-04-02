package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import gameMessages.PlayersConnectedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.client.JoinedPlayersView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class JoinedPlayersControllerTest {
    ClientPlayerController clientPlayerController;
    SocketChannel socketChannel;
    JoinedPlayersView joinedPlayersView;
    PlayersConnectedMessage playersConnectedMessage;
    Workflow workflow;
    JoinedPlayersController joinedPlayersController;

    @Before
    public void setUp() throws Exception {
        clientPlayerController = mock(ClientPlayerController.class);
        socketChannel = mock(SocketChannel.class);
        joinedPlayersView = mock(JoinedPlayersView.class);
        playersConnectedMessage = mock(PlayersConnectedMessage.class);
        workflow = mock(Workflow.class);

        joinedPlayersController = new JoinedPlayersController(workflow, clientPlayerController);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void should_invoke_channel_stop_when_goToHomeScreen_is_called() {

//        joinedPlayersController.goToHomeScreen();
//        verify(socketChannel).stop();
//    }

    @Test
    public void should_invoke_workflow_start_when_goToHomeScreen_is_called() {
        joinedPlayersController.goToHomeScreen();
        verify(workflow).start();
    }
}

