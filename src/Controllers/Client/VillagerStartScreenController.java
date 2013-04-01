package controllers.client;

import channels.messages.ChannelMessage;
import controllers.Workflow;
import gameMessages.VillagerRoleAssigned;
import views.client.VillagerStartScreenView;

public class VillagerStartScreenController {
    private final Workflow workflow;
    private VillagerStartScreenView view;
    private VillagerRoleAssigned message;

    public VillagerStartScreenController(Workflow workflow, ChannelMessage message) {
        this.workflow = workflow;
        this.message = (VillagerRoleAssigned) message;
    }

    public void bind(VillagerStartScreenView view) {
        this.view = view;
    }

    public void start() {
        view.display(message.getPlayersName());
    }
}
