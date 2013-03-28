package controllers.client;

import controllers.Workflow;
import controllers.server.PlayersRoleInfoController;
import views.client.MafiaStartScreenView;

public class MafiaStartScreenController {
    private final Workflow workflow;
    private MafiaStartScreenView view;
    private PlayersRoleInfoController controller;

    public MafiaStartScreenController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void bind(MafiaStartScreenView view) {
        this.view = view;
    }

    public void nightArrived() {
        view.showMafiaNightScreen();
    }
}
