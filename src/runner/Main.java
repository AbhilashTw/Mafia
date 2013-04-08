package runner;

import screens.MafiaViewFactory;
import screens.controls.MainFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Job:Understands to Start up the Mafia Application.
 */

public class Main {
    public static void main(String args[]) throws InvocationTargetException, InterruptedException {

        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                new WorkflowManager(new MafiaViewFactory(), new MainFrame()).start();
            }
        });
    }
}


