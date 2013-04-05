package controllers.server;

import entities.Player;
import entities.Players;
import gameMessages.NightArrivedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class NightControllerTest {
    private NightController controller;
    private Players players;
    private GamePlay play;
    private GamePoll poll;
    private Player player;
    private GamePlayEngine engine;


    @Before
    public void setUp() throws Exception {
        player = mock(Player.class);
        poll = mock(GamePoll.class);
        play = mock(GamePlay.class);
        players = mock(Players.class);
        controller = new NightController(players, play, engine);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void start_invokes_sendMessage_from_Players() {
        controller.start();
        verify(players).sendMessage(new NightArrivedMessage());
    }

    @Test
    public void start_invokes_GamePlays_createPlayersBallot() {
        controller.start();
        verify(play).createPlayersPoll(new GamePoll(), players);
    }

    @Test
    public void pollPlayer_invokes_GamePlay_poll() {
        controller.pollPlayer("Abhilash");
        verify(play).poll("Abhilash");
    }

    @Test
    public void pollPlayer_invokes_GamePlay_mafiaVoteStatus() {
        controller.pollPlayer("Abhilash");
        verify(play).nightPollStatus();
    }



}
