package controllers.client;

import controllers.Workflow;
import runner.WorkflowManager;
import views.client.MafiaView;

/**
 * Job : Understands the Mafia Activities in the game.
 */
public class MafiaController implements ClientEngine {
    private final ClientPlayer clientPlayer;
    private Workflow workflow;
    private MafiaView view;


    public MafiaController(WorkflowManager manager, ClientPlayer clientPlayer) {
        this.workflow = manager;

        this.clientPlayer = clientPlayer;
        clientPlayer.bindClientEngine(this);
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
}
