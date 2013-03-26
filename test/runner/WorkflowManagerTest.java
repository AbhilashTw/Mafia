package runner;

import controllers.HomeController;
import controllers.Workflow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import screens.MafiaViewFactory;
import screens.controls.IMainFrame;
import screens.controls.MainFrame;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WorkflowManagerTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_invoke_controller_start_when_workflow_start_is_called() {
    }

    @Test
    public void start_calls_start_on_HomeController() {
        HomeController mockController = mock(HomeController.class);
        MafiaViewFactory mockViewFactory = mock(MafiaViewFactory.class);

        IMainFrame mockMainFrame = mock(IMainFrame.class);
        WorkflowManager workflowManager = new WorkflowManager(mockViewFactory, mockMainFrame);

        when(mockViewFactory.getHomeController(workflowManager, mockMainFrame)).thenReturn(mockController);

        workflowManager.start();

        verify(mockController).start();
    }
}
