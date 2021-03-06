package controllers.server;

import channels.server.SocketServer;
import controllers.Workflow;
import entities.Player;
import entities.Players;
import gameMessages.PlayersConnectedMessage;
import views.server.StartGameView;


public class StartGameController implements GameEngine {

    private final Workflow workflow;
    private final Players gamePlayers;
    private SocketServer server;
    private StartGameView view;
    private boolean serverStatus = true;

    public StartGameController(Workflow workflow, Players players) {
        this.workflow = workflow;
        gamePlayers = players;
    }

    public void bind(StartGameView view) {
        this.view = view;
    }

    public void addPlayer(Player newPlayer) {
        gamePlayers.addPlayer(newPlayer);
    }

    public String getPlayersListName() {
        return gamePlayers.getPlayersName();
    }

    @Override
    public void playersUpdated() {
        view.updatePlayers(gamePlayers.getPlayers());
        gamePlayers.sendMessage(PlayersConnectedMessage.createPlayersConnectedMessage(getPlayersListName()));

    }

    @Override
    public void removePlayer(Player removedPlayer, GameStatus night) {
        gamePlayers.removePlayer(removedPlayer);
    }

    @Override
    public void updateMafiaVotes(String playerName, String killedPlayer) {
    }

    @Override
    public void updateVillagerVotes(String playerName, String killedPlayer) {
    }

    public boolean start(SocketServer server) {
        this.server = server;
        return server.start();
    }

    public boolean canGameBeStarted() {
        return gamePlayers.getPlayersCount() >= 3;
    }

    public void stop() {
        if (server != null) {
            gamePlayers.quit();
            server.stop();
        }
        workflow.start("Welcome");
    }

    public void startGame() {
        workflow.startGame(gamePlayers);
    }

    public void goToHomeScreen() {
        workflow.start("Server is Already Running");
    }

    public void serverActive(boolean b) {
        serverStatus = b;
    }

    public boolean activeServerExists() {
        return serverStatus;
    }
}
