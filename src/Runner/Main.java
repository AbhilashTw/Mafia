package runner;

import java.io.IOException;

/**
 * Job: Understands invoking point of application.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        new WorkflowManager().start();
    }
}

