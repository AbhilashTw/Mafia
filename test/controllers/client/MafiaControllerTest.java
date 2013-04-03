package controllers.client;

import controllers.Workflow;
import entities.ClientPlayer;
import gameMessages.MafiaVotedOutVillagerMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import views.client.MafiaView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MafiaControllerTest {
    MafiaController controller;
    MafiaView view;
    Workflow workflow;
    ClientPlayer player;

    @Before
    public void setUp() throws Exception {
        workflow = mock(Workflow.class);
        player = mock(ClientPlayer.class);
        view = mock(MafiaView.class);
        controller = new MafiaController(workflow, player);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void displayMafiaVotingChart_invokes_display_from_view_when_called() {
        controller.bind(view);
        controller.displayMafiaVotingChart(new String[]{});
        verify(view).display(new String[]{});
    }

    @Test
    public void sendMessage_invokes_ClientPlayer_sendMessage() {
        MafiaVotedOutVillagerMessage message = mock(MafiaVotedOutVillagerMessage.class);
        controller.bind(view);
        controller.sendMessage(message);
        verify(player).sendMessage(message);
    }
}
