package controllers.client;


import controllers.Workflow;
import gameMessages.PlayerDetailsMessage;
import views.client.JoinedPlayersView;

/**
 * Job: Understands to the other connected players to game.
 */
public class JoinedPlayersController implements ClientEngine {

    private final ClientPlayerController clientPlayerController;
    private final Workflow workflow;
    private JoinedPlayersView view;


    public JoinedPlayersController(Workflow workflow, ClientPlayerController clientPlayerController) {
        this.clientPlayerController = clientPlayerController;
        this.workflow = workflow;
        this.clientPlayerController.bindClientEngine(this);
    }

    public void bind(JoinedPlayersView view) {
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
    public void startVillagerScreen() {
        workflow.startVillagerScreen();
    }

    @Override
    public void startMafiaScreen() {
        workflow.startMafiaScreen();
    }

    @Override
    public void serverClosed() {
        workflow.start();
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
