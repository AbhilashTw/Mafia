package controllers.client;

import controllers.Workflow;
import entities.ClientPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import views.client.VillagerView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class VillagerControllerTest {
    VillagerController controller;
    VillagerView view;
    Workflow workflow;
    ClientPlayer player;

    @Before
    public void setUp() throws Exception {
        workflow = mock(Workflow.class);
        player = mock(ClientPlayer.class);
        view = mock(VillagerView.class);
        controller = new VillagerController(workflow, player);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void displayMafiaVotingChart_is_Called_views_updateStatus_is_invoked() {
        controller.bind(view);
        controller.displayMafiaVotingChart(new String[]{});
        verify(view).updateStatus("Night has arrived\n Good night");
    }

    @Test
    public void displayVillagerVotingChart_invokes_display_from_view_when_called() {
        controller.bind(view);
        controller.displayVillagerVotingChart(new String[]{});
        verify(view).display(new String[]{});
    }

}
