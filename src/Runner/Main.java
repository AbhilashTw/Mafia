package runner;


import controllers.server.Players;
import screens.MafiaViewFactory;
import screens.controls.MainFrame;

/**
 * Job: Understands invoking point of application.
 */

public class Main {
    public static void main(String args[]) {
        new WorkflowManager(new MafiaViewFactory(), new MainFrame(),new Players()).start();
    }
}

