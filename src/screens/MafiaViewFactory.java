package screens;

import controllers.HomeController;
import controllers.Workflow;
import screens.controls.IMainFrame;

public class MafiaViewFactory {

    public HomeController getHomeController(Workflow workflow, IMainFrame mainFrame) {
        HomeController homeController = new HomeController(workflow);
        HomeScreen screen = new HomeScreen(mainFrame, homeController);
        homeController.bind(screen);
        return homeController;
    }
}
