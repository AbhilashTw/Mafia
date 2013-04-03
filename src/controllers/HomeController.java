package controllers;

import views.HomeView;

/**
 * Job:  Understands to control operations on homeScreen.
 */
public class HomeController {
    private final Workflow workflow;
    private HomeView view;

    public HomeController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(HomeView view) {
        this.view = view;
    }

    public void joinServer() {
        workflow.joinServer();
    }

    public void startServer() {
        workflow.startServer();
    }

    public void start() {
    }
}
