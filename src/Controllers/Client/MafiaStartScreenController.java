package controllers.client;

import channels.Messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.PlayersRoleInfoController;
import gameMessages.MafiaRoleAssigned;
import runner.WorkflowManager;
import views.client.MafiaStartScreenView;

public class MafiaStartScreenController {
    private final Workflow workflow;
    private MafiaStartScreenView view;
    private PlayersRoleInfoController controller;
    private MafiaRoleAssigned message;

    public MafiaStartScreenController(WorkflowManager manager, ChannelMessage message) {
        this.workflow = manager;
        this.message = (MafiaRoleAssigned) message;
    }

    public void bind(MafiaStartScreenView view) {
        this.view = view;
    }

    public void start() {
        view.display(message.getPlayersName());
    }

}
