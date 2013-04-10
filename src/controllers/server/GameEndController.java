package controllers.server;

import controllers.Workflow;
import entities.Players;
import gameMessages.MafiaWinsMessage;
import gameMessages.VillagerWinsMessage;
import views.server.GameEndView;

/**
 * Job: Understands the end of game.
 */
public class GameEndController {
    private final Players players;
    private final Workflow workflow;
    private final GameResult gameStatus;
    private final String[] log;
    private GameEndView view;

    public GameEndController(Players players, Workflow workflow, GameResult gameStatus, String[] log) {

        this.players = players;
        this.workflow = workflow;
        this.gameStatus = gameStatus;
        this.log = log;
    }

    public void bind(GameEndView gameEndScreen) {
        this.view = gameEndScreen;
    }

    public void start() {
        if (gameStatus.equals(GameResult.MafiaWins)) sendMafiaWinsMessage();
        else sendVillagerWinsMessage();
        close();
    }

    private void sendVillagerWinsMessage() {
        VillagerWinsMessage message = new VillagerWinsMessage();
        message.setLog(log);
        players.sendMessage(message);
        view.display("Villager Won the Game");
        view.displayLog(log);

    }

    private void sendMafiaWinsMessage() {
        MafiaWinsMessage message = new MafiaWinsMessage();
        message.setLog(log);
        players.sendMessage(new MafiaWinsMessage());
        view.display("Mafia Won the Game");
        view.displayLog(log);

    }

    public void goToHomeScreen() {
        workflow.start("");
    }

    public void close() {
        players.quit();
    }
}
