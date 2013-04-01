package runner;

import screens.MafiaViewFactory;
import screens.controls.MainFrame;

/**
 * Job:Understands to Start up the Mafia Application.
 */

public class Main {
    public static void main(String args[]) {
        new WorkflowManager(new MafiaViewFactory(), new MainFrame()).start();
    }
}

