package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import gameMessages.PlayersConnectedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.client.PlayersConnectedView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayersListControllerTest {
    ClientPlayerController clientPlayerController;
    SocketChannel socketChannel;
    PlayersConnectedView playersConnectedView;
    PlayersConnectedMessage playersConnectedMessage;
    Workflow workflow;
    PlayersListController playersListController;

    @Before
    public void setUp() throws Exception {
        clientPlayerController = mock(ClientPlayerController.class);
        socketChannel = mock(SocketChannel.class);
        playersConnectedView = mock(PlayersConnectedView.class);
        playersConnectedMessage = mock(PlayersConnectedMessage.class);
        workflow = mock(Workflow.class);
        playersListController = new PlayersListController(workflow, clientPlayerController);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void should_invoke_channel_stop_when_goToHomeScreen_is_called() {
//        playersListController.goToHomeScreen();
//      verify(socketChannel).stop();
//    }

    @Test
    public void should_invoke_workflow_start_when_goToHomeScreen_is_called() {
        playersListController.goToHomeScreen();
        verify(workflow).start();
    }
}

