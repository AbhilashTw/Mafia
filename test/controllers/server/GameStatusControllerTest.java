package controllers.server;

import controllers.Workflow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;


public class GameStatusControllerTest {
    GameStatusController controller;
    Players players = mock(Players.class);
    Workflow workflow = mock(Workflow.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sendNightArrivedMessage_Invokes_send_message_from_Players() {

    }


}
