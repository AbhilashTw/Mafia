package controllers.server;

import channels.SocketChannel;
import controllers.Workflow;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import views.server.PlayersRoleInfoView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayersRoleInfoControllerTest {
    private PlayersRoleInfoView view;
    private Workflow workflow;
    private List<Player> players;
    private Player playerOne;
    private PlayersRoleInfoController controller;

    @Before
    public void setUp() throws Exception {
        workflow = mock(Workflow.class);
        view = mock(PlayersRoleInfoView.class);
        players = new ArrayList<Player>();
        playerOne = new Player(mock(SocketChannel.class), mock(GameGod.class));
        players.add(playerOne);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void assignRoles_when_called_invokes_Players_assignRole() {
        controller = new PlayersRoleInfoController(players, workflow);
        controller.assignRoles();
        Assert.assertEquals(true, playerOne.isRoleAssigned());
    }

    @Test
    public void display_Invokes_PlayersInfoScreen_Display() {
        controller = new PlayersRoleInfoController(players, workflow);
        controller.bind(view);
        controller.display();
        verify(view).display(players);
    }

}
