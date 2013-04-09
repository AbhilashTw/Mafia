package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import gameMessages.KilledPlayerMessage;
import views.server.GameStatusView;

/**
 * Job: Understands to Inform the Player about the routine.
 */
public class GameStatusController implements GameEngine, GamePlayEngine {

    private final Workflow workflow;
    private final Players players;
    private GameStatusView view;
    private GamePlay gamePlay;
    private NightController nightController;
    private DayController dayController;


    public GameStatusController(Workflow workflow, Players players, GamePlay gamePlay) {
        this.workflow = workflow;
        this.players = players;
        this.gamePlay = gamePlay;
        players.bindEngine(this);

    }

    public void bind(GameStatusView gameStatusScreen) {
        this.view = gameStatusScreen;
    }

    public void start() {
        view.status("Night Arrived");
        nightController = new NightController(players, gamePlay, this);
        nightController.start();
    }

    @Override
    public void updateMafiaVotes(String playerName, String votedPlayer) {
        view.updateVoteStatus(playerName, votedPlayer);
        nightController.pollPlayer(votedPlayer);

    }


    @Override
    public void removePlayer(Player removedPlayer, GameStatus status) {

    }


    @Override
    public void endGame(GameResult status) {
        workflow.gameEnd(status);
    }


    @Override
    public void startNight() {
        start();
    }

    @Override
    public void updateVillagerVotes(String playerName, String killedPlayer) {
        System.out.println(playerName + " voted " + killedPlayer);
        view.updateVoteStatus(playerName, killedPlayer);
        dayController.poll(killedPlayer);
    }


    @Override
    public void playersUpdated() {
    }


    @Override
    public void removePlayer(Player killedPlayer) {
        view.status(killedPlayer.getName() + "is Killed");
        players.removePlayer(killedPlayer);
    }

    @Override
    public void startDay() {
        view.status("Day Arrived");
        dayController = new DayController(players, this, gamePlay);
        dayController.start();
    }

    public void close() {
        players.quit();
        workflow.start();
    }
}
