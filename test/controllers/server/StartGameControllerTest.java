package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StartGameControllerTest {
    StartGameController controller;
    Workflow workflow = mock(Workflow.class);
    Players players = mock(Players.class);
    Player player = mock(Player.class);

    @Test
    public void addPlayers_invokes_players_addPlayer() {
        controller = new StartGameController(workflow, players);
        controller.addPlayer(player);
        verify(players).addPlayer(player);
    }
}
