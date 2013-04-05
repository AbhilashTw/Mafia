package controllers.client;

import channels.messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.GameResult;
import controllers.server.GameStatus;
import entities.ClientPlayer;
import views.client.MafiaView;

/**
 * Job : Understands the Mafia Activities in the game.
 */
public class MafiaController implements ClientEngine {
    private final ClientPlayer clientPlayer;
    private Workflow workflow;
    private MafiaView view;


    public MafiaController(Workflow workflow, ClientPlayer clientPlayer) {
        this.workflow = workflow;
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
    }

    @Override
    public void startVillagerScreen() {
    }

    @Override
    public void startMafiaScreen() {
    }

    @Override
    public void serverClosed() {
    }

    @Override
    public void PlayerKilled(String playerName) {
        view.updateStatus(playerName + " is Killed");
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
        view.updateStatus("Night Arrived\n You can vote now");
        view.display(playerNames, GameStatus.NIGHT);
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        view.updateStatus("Day Arrived\n You can vote now");
        view.display(playerNames, GameStatus.DAY);
    }

    @Override
    public void showDeadScreen() {
        workflow.showDeadScreen();
    }

    @Override
    public void VillagersWon() {
        workflow.gameEnd(GameResult.VillagerWins);
    }

    @Override
    public void MafiasWon() {
        workflow.gameEnd(GameResult.MafiaWins);
    }

    public void sendMessage(ChannelMessage message) {
        clientPlayer.sendMessage(message);
    }

    public String getClientName(){
       return clientPlayer.getPlayerName();
    }

}
