package controllers.client;

import controllers.Workflow;
import entities.Player;
import gameMessages.KilledMessage;
import org.junit.Test;
import views.client.PlayerDeadView;

import static org.mockito.Mockito.mock;

public class PlayerDeadControllerTest {
    PlayerDeadController controller = mock(PlayerDeadController.class);
    Workflow workflow = mock(Workflow.class);
    PlayerDeadView view = mock(PlayerDeadView.class);
    Player   player  = mock(Player.class);
    KilledMessage message = mock(KilledMessage.class);

    @Test
    public void when_workflow_calls_showDeadScreen_invokes_controllers_start() {
//        controller.goToHomeScreen();
//        verify(workflow).start();
    }

}
