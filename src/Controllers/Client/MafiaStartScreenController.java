package controllers.client;

import controllers.Workflow;
import controllers.server.Players;
import controllers.server.PlayersRoleInfoController;
import views.client.MafiaStartScreenView;

public class MafiaStartScreenController {
    private final Workflow workflow;
    private final Players players;
    private MafiaStartScreenView view;
    private PlayersRoleInfoController controller;

    public MafiaStartScreenController(Workflow workflow, Players players) {
        this.workflow = workflow;
        this.players = players;
    }

    public void bind(MafiaStartScreenView view) {
        this.view = view;
    }

}
