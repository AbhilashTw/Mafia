package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import gameMessages.PlayerDetailsMessage;
import gameMessages.PlayersConnectedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.client.PlayersConnectedView;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayersListControllerTest {
    private final String serverName = "localhost";
    private final String playerName = "Abhilash";
    SocketChannel socketChannel;
    PlayersConnectedView playersConnectedView;
    PlayersConnectedMessage playersConnectedMessage;
    Workflow workflow;
    PlayersListController playersListController;

    @Before
    public void setUp() throws Exception {
        socketChannel = mock(SocketChannel.class);
        playersConnectedView = mock(PlayersConnectedView.class);
        playersConnectedMessage = mock(PlayersConnectedMessage.class);
        workflow = mock(Workflow.class);
        playersListController = new PlayersListController(workflow, socketChannel, serverName, playerName);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_invoke_displayConnectedPlayers_when_onMessageArrived_is_called_on_message_being_a_playerConnectedMessage() {
        playersListController.bind(playersConnectedView);
        playersListController.onNewMessageArrived(socketChannel, playersConnectedMessage);
        verify(playersConnectedView).displayConnectedPlayers(playersConnectedMessage.getPlayersConnected());
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_RuntimeException_When_OnSendFailed_Is_Called() {
        playersListController.onSendFailed(socketChannel, new IOException(), playersConnectedMessage);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_RuntimeException_When_OnClose_Is_Called() {
        playersListController.onClose(socketChannel, new IOException());
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_RuntimeException_When_OnMessageReadError_Is_Called() {
        playersListController.onMessageReadError(socketChannel, new IOException());
    }

    @Test
    public void should_Invoke_View_ConnectedToServer_When_PlayerControlList_Start_is_Called() {
        playersListController.bind(playersConnectedView);
        playersListController.start();
        verify(playersConnectedView).connectedToServer(serverName, playerName);
    }

    @Test
    public void invokes_channels_send_method_on_calling_playerListController_start() {
        playersListController.bind(playersConnectedView);
        playersListController.start();
        verify(socketChannel).send(PlayerDetailsMessage.createPlayerDetailsMessage(playerName));
    }

    @Test
    public void should_invoke_channel_stop_when_goToHomeScreen_is_called() {
        playersListController.goToHomeScreen();
        verify(socketChannel).stop();
    }

    @Test
    public void should_invoke_workflow_start_when_goToHomeScreen_is_called() {
        playersListController.goToHomeScreen();
        verify(workflow).start();
    }


}

