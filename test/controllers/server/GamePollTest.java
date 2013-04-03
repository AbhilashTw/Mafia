package controllers.server;

import entities.Player;
import entities.Players;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

/**
 * Job: Ensures the correctness and behaviour of  GamePoll.
 */
public class GamePollTest {

    GamePoll gamePoll;
    Player abhilash = mock(Player.class);
    Player sneha = mock(Player.class);
    Players players;

    @Before
    public void setUp() throws Exception {
        players = new Players();
        players.addPlayer(abhilash);
        players.addPlayer(sneha);
        gamePoll = new GamePoll();
        gamePoll.createPlayerBallot(players);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getPlayerVotedOut_gives_player_who_has_maximum_vote() {
        stub(abhilash.getName()).toReturn("Abhilash");
        stub(sneha.getName()).toReturn("Sneha");
        gamePoll.poll("Abhilash");
        Assert.assertEquals(abhilash, gamePoll.getPlayerVotedOut());
    }

    @Test(expected = IllegalArgumentException.class)
    public void player_name_is_null_throws_IllegalArgumentException() {
        gamePoll.poll(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void players_is_null_throws_IllegalArgumentException() {
        gamePoll.createPlayerBallot(null);
    }
}
