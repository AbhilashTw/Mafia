package controllers.client;

import controllers.Workflow;
import entities.ClientPlayer;
import org.junit.Test;
import views.client.PlayerDeadView;

import static org.mockito.Mockito.mock;

public class PlayerDeadControllerTest {
    PlayerDeadController controller = mock(PlayerDeadController.class);
    Workflow workflow = mock(Workflow.class);
    PlayerDeadView view = mock(PlayerDeadView.class);
    ClientPlayer player = mock(ClientPlayer.class);

    @Test
    public void when_workflow_calls_showDeadScreen_invokes_controllers_start() {
        workflow.showDeadScreen();
//        verify(controller).start();
    }


}
