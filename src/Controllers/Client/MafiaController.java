package controllers.client;

import controllers.Workflow;
import gameMessages.MafiaVotedOutVillagerMessage;
import views.client.MafiaView;

/**
 * Job : Understands the Mafia Activities in the game.
 */
public class MafiaController implements ClientEngine {
    private final ClientPlayerController clientPlayerController;
    private Workflow workflow;
    private MafiaView view;


    public MafiaController(Workflow workflow, ClientPlayerController clientPlayerController) {
        this.workflow = workflow;
        this.clientPlayerController = clientPlayerController;
        clientPlayerController.bindClientEngine(this);
    }

    public void bind(MafiaView view) {
        this.view = view;
    }

    public void start() {
    }

    @Override
    public void displayConnectedPlayers(String[] playersConnected) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startVillagerScreen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startMafiaScreen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void serverClosed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
        view.display(playerNames);
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
    }

    public void sendMessage(MafiaVotedOutVillagerMessage message) {
        clientPlayerController.sendMessage(message);
    }
}
