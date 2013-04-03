package controllers.server;

import entities.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Job :Ensures the correctness and behaviours of PlayerBallot.
 */
public class PlayersBallotTest {
    Player player;
    PlayersBallot ballot;

    @Before
    public void setUp() throws Exception {
        player = mock(Player.class);
        ballot = PlayersBallot.createPlayersBallot(player);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getBallotCount_gets_totalVote_of_the_Player() {
        Assert.assertEquals(0, ballot.getBallotCount());
    }

    @Test
    public void getBallotCount_gets_totalVote_as_one_on_poll() {
        ballot.poll();
        Assert.assertEquals(1, ballot.getBallotCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void player_as_null_throws_IllegalArgumentException() {
        PlayersBallot.createPlayersBallot(null);
    }

    @Test
    public void isSamePlayer_Invokes_Player_getName() {
        stub(player.getName()).toReturn("Abhilash");
        ballot.isSamePlayer("Abhilash");
        verify(player).getName();
    }

    @Test
    public void isSamePlayer_tells_whether_playerName_matches_playerName_returns_true() {
        stub(player.getName()).toReturn("Abhilash");
        Assert.assertEquals(true, ballot.isSamePlayer("Abhilash"));
    }

    @Test
    public void isSamePlayer_tells_whether_playerName_does_not_match_playerName_returns_false() {
        stub(player.getName()).toReturn("Abhi");
        Assert.assertEquals(false, ballot.isSamePlayer("Abhilash"));
    }

    @Test
    public void getPlayer_gives_the_player_from_the_ballot() {
        Assert.assertEquals(player, ballot.getPlayer());
    }
}
