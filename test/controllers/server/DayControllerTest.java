package controllers.server;

import entities.Player;
import entities.Players;
import gameMessages.DayArrivedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DayControllerTest {

    DayController controller;
    Players players;
    Player player;
    GamePlayEngine engine;
    GamePlay play;


    @Before
    public void setUp() throws Exception {
        play = mock(GamePlay.class);
        players = mock(Players.class);
        controller = new DayController(players, engine, play);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void start_invokes_players_send_message() {
        controller.start();
        verify(players).sendMessage(new DayArrivedMessage());
    }

    @Test
    public void start_invokes_GamePlays_createPlayersBallot() {
        controller.start();
        verify(play).createPlayersPoll(new GamePoll(), players);
    }

    @Test
    public void poll_invokes_GamePlay_poll() {
        controller.poll("Abhilash");
        verify(play).poll("Abhilash");
    }


}
