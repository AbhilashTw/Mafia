package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import entities.ClientPlayer;
import gameMessages.PlayersConnectedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.client.JoinedPlayersView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class JoinedPlayersControllerTest {
    ClientPlayer clientPlayer;
    SocketChannel socketChannel;
    JoinedPlayersView joinedPlayersView;
    PlayersConnectedMessage playersConnectedMessage;
    Workflow workflow;
    JoinedPlayersController joinedPlayersController;

    @Before
    public void setUp() throws Exception {
        clientPlayer = mock(ClientPlayer.class);
        socketChannel = mock(SocketChannel.class);
        joinedPlayersView = mock(JoinedPlayersView.class);
        playersConnectedMessage = mock(PlayersConnectedMessage.class);
        workflow = mock(Workflow.class);

        joinedPlayersController = new JoinedPlayersController(workflow, clientPlayer);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void should_invoke_workflow_start_when_goToHomeScreen_is_called() {
        joinedPlayersController.goToHomeScreen();
        verify(workflow).start("Welcome");
    }

    @Test
    public void should_invoke_clientPlayerController_stop_when_goToHomeScreen_is_called() {
        joinedPlayersController.goToHomeScreen();
        verify(clientPlayer).stop();
    }

    @Test
    public void should_invoke_workflow_startVillagerScreen_when_startVillagerScreen_is_called() {
        joinedPlayersController.startVillagerScreen();
        verify(workflow).startVillagerScreen();
    }

    @Test
    public void should_invoke_workflow_startMafiaScreen_when_startMafiaScreen_is_called() {
        joinedPlayersController.startMafiaScreen();
        verify(workflow).startMafiaScreen();
    }


}

