package controllers.server;

import gameMessages.NightArrivedMessage;
import runner.WorkflowManager;
import screens.server.GameStatusScreen;
import views.server.GameStatusView;

/*
   Job: Understands to Inform the Player about the routine.
*/
public class GameStatusController implements GameStatusView {

    private final WorkflowManager workflowManager;
    private final Players players;
    private GameStatusScreen gameStatusScreen;

    public GameStatusController(WorkflowManager workflowManager, Players players) {

        this.workflowManager = workflowManager;
        this.players = players;
    }

    public void bind(GameStatusScreen gameStatusScreen) {
        this.gameStatusScreen = gameStatusScreen;
    }

    public void start() {
        sendNightArrivedMessage();
    }

    private void sendNightArrivedMessage() {
        NightArrivedMessage message = new NightArrivedMessage();
        message.setPlayersName(players.getAllPlayersName());
        players.sendMessage(message);
    }
}
