package controllers.client;

import controllers.Workflow;
import views.client.PlayerDeadView;

public class PlayerDeadController {
    private final Workflow workflow;
    private PlayerDeadView view;

    public PlayerDeadController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void bind(PlayerDeadView view) {
        this.view = view;
    }

    public void start() {

    }
}
