package controllers;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Job: ensures the correctness of behaviour of homeController.
 */

public class HomeControllerTest {
    @Test
    public void should_Invoke_startServer_When_HomeController_startServer_is_Called() {
        Workflow workflow = mock(Workflow.class);
        HomeController homeController = new HomeController(workflow);
        homeController.startServer();
        verify(workflow).startServer();
    }

    @Test
    public void should_Invoke_joinServer_When_HomeController_joinServer_is_Called() {
        Workflow workflow = mock(Workflow.class);
        HomeController homeController = new HomeController(workflow);
        homeController.joinServer();
        verify(workflow).joinServer();
    }
}
