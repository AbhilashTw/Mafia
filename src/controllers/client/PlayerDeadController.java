package controllers.client;

import controllers.Workflow;
import entities.ClientPlayer;
import views.client.PlayerDeadView;

public class PlayerDeadController implements ClientEngine {
    private final Workflow workflow;
    private final ClientPlayer clientPlayer;
    private final String[] log;
    private PlayerDeadView view;

    public PlayerDeadController(Workflow workflow, ClientPlayer clientPlayer, String[] log) {

        this.workflow = workflow;
        this.clientPlayer = clientPlayer;
        this.log = log;
        clientPlayer.bindClientEngine(this);
    }

    public void bind(PlayerDeadView view) {
        this.view = view;
    }

    public void start() {
        view.display(clientPlayer.getPlayerName());
        view.displayLog(log);
    }

    public void goToHomeScreen() {
        workflow.start("Welcome");
    }


    @Override
    public void displayDeadLog(String[] presentStatusLog) {
        view.displayLog(presentStatusLog);
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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void PlayerKilled(String playerName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showDeadScreen(String[] log) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void VillagersWon() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void MafiasWon() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayMafia(String[] players) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayPlayers(String[] allPlayersName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
