package runner;

import channels.messages.ChannelMessage;
import controllers.server.Player;
import controllers.server.Players;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Job:- Ensures correctness of behaviour of players
 */
public class PlayersTest {
    Players players;
    Player player;

    @Before
    public void setUp() throws Exception {
        players = new Players();
        player = mock(Player.class);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void adding_a_null_valued_player_throws_illegalArgumentException() {
        players = new Players();
        players.addPlayer(null);
    }

    @Test
    public void on_calling_getPlayerNames_getName_of_Player_gets_invoked_and_returns_name() {
        when(player.getName()).thenReturn("Abhilash");
        players.addPlayer(player);
        players.getPlayersName();
        verify(player).getName();
        Assert.assertEquals("Abhilash\n", players.getPlayersName());
    }

    @Test
    public void when_sendMessage_is_called_sendMessahe_from_Players_is_invoked() {
        players.addPlayer(player);
        ChannelMessage message = new ChannelMessage();
        players.sendMessage(message);
        verify(player).sendMessage(message);
    }

    @Test
    public void adding_a_player_getPlayersCount_returns_number_of_players() {
        players.addPlayer(player);
        Assert.assertEquals(1, players.getPlayersCount());
    }

    @Test
    public void quit_from_Players_invokes_stop_from_Player() {
        players.addPlayer(player);
        players.quit();
        verify(player).stop();
    }

    @Test
    public void removePlayer_removes_a_Player_from_the_list_of_players() {
        players.addPlayer(player);
        players.removePlayer(player);
        Assert.assertEquals(0, players.getPlayersCount());
    }
}
