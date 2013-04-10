package controllers.client;

import controllers.Workflow;
import entities.ClientPlayer;
import views.client.PlayerDeadView;

public class PlayerDeadController {
    private final Workflow workflow;
    private final ClientPlayer clientPlayer;
    private PlayerDeadView view;

    public PlayerDeadController(Workflow workflow, ClientPlayer clientPlayer) {

        this.workflow = workflow;
        this.clientPlayer = clientPlayer;
    }

    public void bind(PlayerDeadView view) {
        this.view = view;
    }

    public void start() {
        view.display(clientPlayer.getPlayerName());
    }

    public void goToHomeScreen() {
        workflow.start("Welcome");
    }

}
