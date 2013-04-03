package controllers.server;

import controllers.Workflow;
import entities.Players;
import gameMessages.NightArrivedMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.server.GameStatusView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class GameStatusControllerTest {
    GamePoll gamePoll = mock(GamePoll.class);
    GameStatusController controller;
    GameStatusView view = mock(GameStatusView.class);
    Players players = mock(Players.class);
    Workflow workflow = mock(Workflow.class);

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sendNightArrivedMessage_Invokes_send_message_from_Players() {
        controller.start();
        verify(players).sendMessage(new NightArrivedMessage());
    }

    @Test
    public void when_updateMafiaVotes_is_called_invokes_view_updatePlayer() {
        controller.bind(view);
        controller.updateMafiaVotes("Abhilash", "Sneha");
        verify(view).updateMafiaVotingStatus("Abhilash", "Sneha");
    }

}
