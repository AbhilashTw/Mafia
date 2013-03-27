package controllers.client;

import runner.WorkflowManager;
import views.client.MafiaStartScreenView;

public class MafiaStartScreenController {
    private final WorkflowManager workflowManager;
    private MafiaStartScreenView view;

    public MafiaStartScreenController(WorkflowManager workflowManager) {
        this.workflowManager = workflowManager;
    }

    public void bind(MafiaStartScreenView view) {
        this.view = view;
    }
}
