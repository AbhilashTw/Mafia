package controllers.client;

import channels.messages.ChannelMessage;
import controllers.Workflow;

import gameMessages.PlayerDetailsMessage;
import views.client.PlayersConnectedView;


public class PlayersListController implements ClientEngine {

    private final ClientPlayerController clientPlayerController;
    private final Workflow workflow;
    private PlayersConnectedView view;

    public PlayersListController(Workflow workflow, ClientPlayerController clientPlayerController) {
        this.clientPlayerController = clientPlayerController;
        this.workflow = workflow;
        this.clientPlayerController.bindClientEngine(this);
    }

    public void bind(PlayersConnectedView view) {
        this.view = view;
    }

    public void start() {
        view.connectedToServer(clientPlayerController.getServerName(), clientPlayerController.getPlayerName());
        clientPlayerController.sendMessage(PlayerDetailsMessage.createPlayerDetailsMessage(clientPlayerController.getPlayerName()));
    }

    public void goToHomeScreen() {
        clientPlayerController.stop();
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
    public void serverClosed() {
        workflow.start();
    }
}
