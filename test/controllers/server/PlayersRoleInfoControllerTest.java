package controllers.server;

import channels.SocketChannel;
import controllers.Workflow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.server.PlayersRoleInfoView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayersRoleInfoControllerTest {
    private PlayersRoleInfoView view;
    private Workflow workflow;
    private Players players;
    private Player playerOne;
    private PlayersRoleInfoController controller;

    @Before
    public void setUp() throws Exception {
        players = mock(Players.class);
        workflow = mock(Workflow.class);
        view = mock(PlayersRoleInfoView.class);
        playerOne = new Player(mock(SocketChannel.class), mock(GameGod.class));
        players.addPlayer(playerOne);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void when_assign_Role_is_called_the_Players_assign_role_is_invoke() {
        controller = new PlayersRoleInfoController(players, workflow);
        controller.assignRoles();
        verify(players).assignRoles();
    }

    @Test
    public void when_display_is_called_the_view_display_is_invoke() {
        controller = new PlayersRoleInfoController(players, workflow);
        controller.bind(view);
        controller.display();
        verify(view).display(players.getPlayers());
    }


}
