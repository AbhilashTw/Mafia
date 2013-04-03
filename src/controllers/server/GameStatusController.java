package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import views.server.GameStatusView;

/**
 * Job: Understands to Inform the Player about the routine.
 */
public class GameStatusController implements GameEngine {

    private final Workflow workflowManager;
    private final Players players;
    private GameStatusView view;
    private GamePlay gamePlay;


    public GameStatusController(Workflow workflowManager, Players players, GamePlay gamePlay) {
        this.workflowManager = workflowManager;
        this.players = players;
        this.gamePlay = gamePlay;
        players.bindEngine(this);
    }

    public void bind(GameStatusView gameStatusScreen) {
        this.view = gameStatusScreen;
    }

    public void start() {
        gamePlay.sendNightArrivedMessage();


    }

    @Override
    public void playersUpdated() {
    }

    @Override
    public void removePlayer(Player player) {
    }

    @Override
    public void updateMafiaVotes(String playerName, String killedPlayer) {
        view.updateMafiaVotingStatus(playerName, killedPlayer);
        gamePlay.poll(killedPlayer);
        if (gamePlay.mafiaPollStatus()) removePlayer(gamePlay.getKilledPlayer());
        if (gamePlay.getGameStatus().equals(GameResult.GameStable)) start();
    }


}
