package controllers.client;


import controllers.Workflow;
import entities.ClientPlayer;
import gameMessages.PlayerDetailsMessage;
import views.client.JoinedPlayersView;

import javax.swing.*;

/**
 * Job: Understands to the other connected players to game.
 */
public class JoinedPlayersController implements ClientEngine {

    private final ClientPlayer clientPlayer;
    private final Workflow workflow;
    private JoinedPlayersView view;


    public JoinedPlayersController(Workflow workflow, ClientPlayer clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.workflow = workflow;
        this.clientPlayer.bindClientEngine(this);
    }

    public void bind(JoinedPlayersView view) {
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
    public void startVillagerScreen() {
        workflow.startVillagerScreen();
    }

    @Override
    public void startMafiaScreen() {
        workflow.startMafiaScreen();
    }

    @Override
    public void serverClosed() {
        view.connectionClosed();
        workflow.start();
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
    }

    @Override
    public void PlayerKilled(String playerName) {
    }

    @Override
    public void showDeadScreen() {
    }

    @Override
    public void VillagersWon() {
    }

    @Override
    public void MafiasWon() {
    }

    @Override
    public void displayMafia(String[] players) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
