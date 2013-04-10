package controllers.client;

import controllers.Workflow;
import controllers.server.GameResult;
import entities.ClientPlayer;
import gameMessages.VillagerVotedOutMafiaMessage;
import views.client.VillagerView;

public class VillagerController implements ClientEngine {
    private final Workflow workflow;
    private final ClientPlayer clientPlayer;
    private VillagerView view;

    public VillagerController(Workflow workflow, ClientPlayer clientPlayer) {
        this.workflow = workflow;
        this.clientPlayer = clientPlayer;
        clientPlayer.bindClientEngine(this);
    }

    public void bind(VillagerView view) {
        this.view = view;
    }

    public void start() {
    }

    @Override
    public void displayConnectedPlayers(String[] playersConnected) {
    }

    @Override
    public void startVillagerScreen() {

    }

    @Override
    public void startMafiaScreen() {
    }

    @Override
    public void serverClosed() {
        view.serverClosed();
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
        view.updateStatus("Night arrived.");
        view.updateStatus("Villagers go to sleep");
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        view.updateStatus("Villagers Wake Up ! !");
        view.display(playerNames);
    }


    @Override
    public void VillagersWon(String[] log) {
        workflow.gameEnd(GameResult.VillagerWins, log);
    }

    @Override
    public void MafiasWon(String[] log) {
        workflow.gameEnd(GameResult.MafiaWins, log);
    }

    @Override
    public void displayMafia(String[] players) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayPlayers(String[] allPlayersName) {
        view.displayPlayers(allPlayersName);
    }

    @Override
    public void displayDeadLog(String[] presentStatusLog) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void PlayerKilled(String playerName) {
        view.updateStatus(playerName + " is Killed");
        view.updateStatus("Whom do you want to suspect?");
    }

    @Override
    public void showDeadScreen(String[] log) {
        workflow.showDeadScreen(log);
    }

    public void sendMessage(VillagerVotedOutMafiaMessage message) {
        clientPlayer.sendMessage(message);
    }

    public String getClientName() {
        return clientPlayer.getPlayerName();
    }
}
