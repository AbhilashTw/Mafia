package controllers.client;

import channels.Messages.ChannelMessage;
import controllers.Workflow;
import gameMessages.PlayerDetailsMessage;
import runner.WorkflowManager;
import views.client.PlayersConnectedView;

/**
 * Job:-
 */
public class PlayersListController implements ClientEngine {

    private final ClientPlayer clientPlayer;
    private final Workflow workflow;
    private PlayersConnectedView view;

    public PlayersListController(Workflow workflow, ClientPlayer clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.workflow = workflow;
        this.clientPlayer.bindClientEngine(this);
    }

    public void bind(PlayersConnectedView view) {
        this.view = view;
    }

    public void start() {
        view.connectedToServer(clientPlayer.getServerName(), clientPlayer.getPlayerName());
        clientPlayer.sendMessage(PlayerDetailsMessage.createPlayerDetailsMessage(clientPlayer.getPlayerName()));
    }

    public void goToHomeScreen() {
        clientPlayer.stop();
        workflow.start();
    }

    @Override
    public void displayConnectedPlayers(String[] playersConnected) {
        view.displayConnectedPlayers(playersConnected);
    }

    @Override
    public void startVillagerScreen(ChannelMessage message) {
        workflow.startVillagerScreen(message);
    }

    @Override
    public void startMafiaScreen(ChannelMessage message) {
        workflow.startMafiaScreen(message);
    }

    @Override
    public void ServerClosed() {
        workflow.start();
    }
}
