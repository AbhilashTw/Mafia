package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.server.GameStatusView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
  Job: Ensures correctness and behaviour of GameStatusController.
 */
public class GameStatusControllerTest {
    GameStatusView view = mock(GameStatusView.class);
    Players players = mock(Players.class);
    Workflow workflow = mock(Workflow.class);
    GameStatusController controller;
    GamePlay play = mock(GamePlay.class);
    Player player = mock(Player.class);

    @Before
    public void setUp() throws Exception {
        controller = new GameStatusController(workflow, players, play);
    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void start_Invokes_players_getAllPlayerNames() {
//        controller.start();
//        verify(players).getAllPlayersName();
//    }
//
//    @Test
//    public void updateMafiaVotes_invokes_views_updateStatus() {
//        controller.bind(view);
//        controller.updateMafiaVotes("Abhilash", "Sneha");
//        verify(view).updateVoteStatus("Abhilash", "Sneha");
//    }
//
//    @Test
//    public void updateMafiaVotes_invokes_GamePlays_poll() {
//        controller.bind(view);
//        controller.updateMafiaVotes("Abhilash", "Sneha");
//        verify(play).poll("Sneha");
//    }
//
//    @Test
//    public void updateMafiaVotes_Invokes_mafiaPollStatus_from_GamePlay() {
//        controller.bind(view);
//        controller.updateMafiaVotes("", "");
//        verify(play).nightPollStatus();
//    }

}