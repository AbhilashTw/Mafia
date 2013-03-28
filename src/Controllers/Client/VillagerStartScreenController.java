package controllers.client;

import controllers.Workflow;
import views.client.VillagerStartScreenView;

public class VillagerStartScreenController {
    private final Workflow workflow;
    private VillagerStartScreenView view;

    public VillagerStartScreenController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(VillagerStartScreenView view) {
        this.view = view;
    }
}
