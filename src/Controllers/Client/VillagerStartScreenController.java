package controllers.client;

import runner.WorkflowManager;
import views.client.VillagerStartScreenView;

public class VillagerStartScreenController {
    private final WorkflowManager workflowManager;
    private VillagerStartScreenView view;

    public VillagerStartScreenController(WorkflowManager workflowManager) {

        this.workflowManager = workflowManager;
    }

    public void bind(VillagerStartScreenView view) {

        this.view = view;
    }
}
