package controllers.client;

import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.server.Players;
import controllers.server.StartGameController;
import gameMessages.PlayersConnectedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientPlayerTest {

    SocketChannel socketChannel;
    ClientEngine engine;
    ClientPlayer player;
    StartGameController controller;
    Exception e;
    Players  players;
    PlayersConnectedMessage message;

    @Before
    public void setUp() throws Exception {
        engine = mock(ClientEngine.class);
        socketChannel = mock(SocketChannel.class);
        player = mock(ClientPlayer.class);
        e = mock(Exception.class);
        controller=mock(StartGameController.class);
        message = mock(PlayersConnectedMessage.class);
        players=mock(Players.class);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void test() {
//        players.sendMessage(message);
//        player.onNewMessageArrived(socketChannel, message);
//        verify(engine).displayConnectedPlayers(new String[]{});
//    }

//    @Test
//    public void test_() {
//        player.sendMessage(message);
//        verify(engine).
//    }

}
