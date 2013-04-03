package controllers.server;

import controllers.Workflow;
import entities.Players;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import views.server.GameStatusView;

import static org.mockito.Mockito.mock;


public class GameStatusControllerTest {
    GamePoll gamePoll = mock(GamePoll.class);
    GameStatusController controller;
    GameStatusView view = mock(GameStatusView.class);
    Players players = mock(Players.class);
    Workflow workflow = mock(Workflow.class);

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }


}
