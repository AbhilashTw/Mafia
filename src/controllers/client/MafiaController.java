package controllers.client;

import channels.messages.ChannelMessage;
import controllers.Workflow;
import entities.ClientPlayer;
import gameMessages.MafiaVotedOutVillagerMessage;
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
    public void PlayerKilled(String playerName) {
        view.updateStatus(playerName + " is Killed");
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
        view.updateStatus("Night Arrived");
        view.display(playerNames);
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        view.updateStatus("Day Arrived");
        view.display(playerNames);
    }

    @Override
    public void PlayerDead() {
        workflow.startPlayerDeadScreen();
    }

    public void sendMessage(ChannelMessage message) {
        clientPlayer.sendMessage(message);
    }

}
