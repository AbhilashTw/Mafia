package controllers.server;

import controllers.Workflow;
import entities.Players;
import gameMessages.MafiaWinsMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.server.GameEndView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameEndControllerTest {
    GameEndController controller;
    GameEndView view;
    Players players;
    Workflow workflow;

    @Before
    public void setUp() throws Exception {
        view = mock(GameEndView.class);
        players = mock(Players.class);
        workflow = mock(Workflow.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void close_invokes_players_stop() {
        controller = new GameEndController(players, workflow, GameResult.MafiaWins);
        controller.close();
        verify(players).quit();
    }

    @Test
    public void goTOHomeScreen_invokes_workflow_start() {
        controller = new GameEndController(players, workflow, GameResult.MafiaWins);
        controller.goToHomeScreen();
        verify(workflow).start();
    }


}
