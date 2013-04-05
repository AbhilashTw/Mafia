package controllers.server;

import entities.Players;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Job: Ensures correctness of behaviour of GamePlay.
 */
public class GamePlayTest {

    GamePlay gamePlay;
    Players players;
    GamePoll poll;

    @Before
    public void setUp() throws Exception {
        players = mock(Players.class);
        gamePlay = new GamePlay();
        poll = mock(GamePoll.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createPlayersPoll_invokes_GamePoll_createPlayerBallot() {
        gamePlay.createPlayersPoll(poll, players);
        verify(poll).createPlayerBallot(players);
    }

    @Test
    public void getPollCount_invokes_poll_getPollCount() {
        gamePlay.createPlayersPoll(poll, players);
        gamePlay.getPollCount();
        verify(poll).getPollCount();
    }

    @Test
    public void poll_invokes_poll_from_GamePoll() {
        gamePlay.createPlayersPoll(poll, players);
        gamePlay.poll("Abhilash");
        verify(poll).poll("Abhilash");
    }

    @Test
    public void mafiaPollStatus_invokes_getPollCount_from_GamePoll() {
        gamePlay.createPlayersPoll(poll, players);
        gamePlay.nightPollStatus();
        verify(poll).getPollCount();
    }

    @Test
    public void getKilledPlayer_invokes_getPlayerVotedOut_from_GamePoll() {
        gamePlay.createPlayersPoll(poll, players);
        gamePlay.getKilledPlayer();
        verify(poll).getPlayerVotedOut();
    }

    @Test
    public void getGameStatus_tells_present_game_Status() {
        gamePlay.createPlayersPoll(poll, players);
        Assert.assertEquals(GameResult.VillagerWins, gamePlay.getGameStatus());
    }


}
