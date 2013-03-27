package runner;


import screens.MafiaViewFactory;
import screens.controls.MainFrame;

/**
 * Job: Understands invoking point of application.
 */

public class Main {
    public static void main(String args[]) {
        new WorkflowManager(new MafiaViewFactory(), new MainFrame()).start();
//        new WorkflowManager(new MafiaViewFactory(),new MainFrame()).startVillagerScreen();
//        new WorkflowManager(new MafiaViewFactory(),new MainFrame()).startMafiaScreen();

    }
}

