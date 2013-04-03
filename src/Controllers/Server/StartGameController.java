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
    public void removePlayer(Player player) {
        gamePlayers.removePlayer(player);

    }

    @Override
    public void updateMafiaVotes(String playerName, String killedPlayer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void start(SocketServer server) {
        this.server = server;
        server.start();
    }

    public boolean canGameBeStarted() {
        return gamePlayers.getPlayersCount() > 0;
    }

    public void stop() {
        if (server != null) {
            gamePlayers.quit();
            server.stop();
        }
        workflow.start();
    }

    public void startGame() {
        workflow.startGame(gamePlayers);
    }
}
