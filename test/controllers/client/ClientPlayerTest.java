package controllers.client;

import channels.SocketChannel;
import controllers.server.StartGameController;
import entities.ClientPlayer;
import entities.Players;
import gameMessages.PlayersConnectedMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ClientPlayerTest {
    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }

    SocketChannel socketChannel;
    ClientEngine engine;
    ClientPlayer player;
    StartGameController controller;
    Exception e;
    Players players;
    PlayersConnectedMessage message;

    @Before
    public void setUp() throws Exception {
        engine = mock(ClientEngine.class);
        socketChannel = mock(SocketChannel.class);
        player = mock(ClientPlayer.class);
        e = mock(Exception.class);
        controller = mock(StartGameController.class);
        message = mock(PlayersConnectedMessage.class);
        players = mock(Players.class);
    }

    @After
    public void tearDown() throws Exception {

    }


}
