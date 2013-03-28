package controllers.server;

import channels.SocketChannel;
import gameMessages.PlayerDetailsMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayerTest {
    Player player;
    SocketChannel socketChannel;
    GameEngine gameEngine;
    PlayerDetailsMessage message;

    @Before
    public void setUp() throws Exception {
        socketChannel = mock(SocketChannel.class);
        gameEngine = mock(GameEngine.class);
        player = new Player(socketChannel, gameEngine);
        message = mock(PlayerDetailsMessage.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void when_sendMessage_is_called_it_invokes_send_from_Channel() {
        player.sendMessage(message);
        verify(socketChannel).send(message);
    }

    @Test
    public void on_calling_onClose_invokes_Stop_from_Channel() {
        player.onClose(socketChannel, new Exception());
        verify(socketChannel).stop();
    }

    @Test
    public void on_calling_onClose_invokes_removePlayer_from_GameGod() {
        player.onClose(socketChannel, new Exception());
        verify(gameEngine).removePlayer(player);
    }

    @Test
    public void on_calling_onClose_invokes_playersUpdated_from_GameGod() {
        player.onClose(socketChannel, new Exception());
        verify(gameEngine).playersUpdated();
    }

    @Test
    public void on_calling_onNewMessageArrived_invokes_playersUpdated_from_GameGod() {
        player.onNewMessageArrived(socketChannel, message);
        verify(gameEngine).playersUpdated();
    }

    @Test
    public void on_calling_onNewMessageArrived_invokes_getPlayerNames_from_PlayerDetailsMessage() {
        player.onNewMessageArrived(socketChannel, message);
        verify(message).getPlayerName();
    }

    @Test
    public void set_Player_role_mafia_and_get_returns_mafia_as_text() {
        player.setRole(Role.Mafia);
        Assert.assertEquals(Role.Mafia.toString(), player.getRole());
    }

}
