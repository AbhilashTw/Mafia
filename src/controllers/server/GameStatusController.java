package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import gameMessages.GameStatusLogMessage;
import views.server.GameStatusView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

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
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        view.status(format.format(cal.getTime()) + " Night Arrived");
        view.updatePlayersList(players);
        nightController = new NightController(players, gamePlay, this);
        nightController.start();
    }

    @Override
    public void updateMafiaVotes(String playerName, String votedPlayer) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        view.updateVoteStatus(playerName, votedPlayer, format.format(cal.getTime()));
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
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        view.updateVillagerVotes(format.format(cal.getTime()), playerName, killedPlayer);
        dayController.poll(killedPlayer);
    }


    @Override
    public void playersUpdated() {
    }


    @Override
    public void removePlayer(final Player killedPlayer) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        view.status(format.format(cal.getTime()) + " " + killedPlayer.getName() + " is Killed");
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                sendLogMessage(killedPlayer);
            }
        }, 500);
        players.removePlayer(killedPlayer);
    }

    private void sendLogMessage(Player player) {
        GameStatusLogMessage message = new GameStatusLogMessage();
        message.setLog(view.getPresentStatusLog());
        player.sendMessage(message);
    }

    @Override
    public void startDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        view.status(format.format(cal.getTime()) + " Day Arrived");
        view.updatePlayersList(players);
        dayController = new DayController(players, this, gamePlay);
        dayController.start();
    }

    public void close() {
        players.quit();
        workflow.start("");
    }
}
