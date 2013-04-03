package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import gameMessages.DayArrivedMessage;
import gameMessages.KilledMessage;
import gameMessages.KilledPlayerMessage;
import gameMessages.NightArrivedMessage;
import views.server.GameStatusView;

/**
 * Job: Understands to Inform the Player about the routine.
 */
public class GameStatusController implements GameEngine {

    private final Workflow workflow;
    private final Players players;
    private GameStatusView view;
    private GamePlay gamePlay;


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
        sendNightArrivedMessage();
        gamePlay.createPlayersPoll(new GamePoll(), players);
    }

    private void sendNightArrivedMessage() {
        NightArrivedMessage message = new NightArrivedMessage();
        message.setPlayersName(players.getAllPlayersName());
        players.sendMessage(message);
    }

    @Override
    public void playersUpdated() {
    }

    @Override
    public void removePlayer(Player removedPlayer) {
        removedPlayer.sendMessage(new KilledMessage());
        players.removePlayer(removedPlayer);
        sendKilledPlayerMessage(removedPlayer);
        view.updatePlayerKilledStatus(removedPlayer.getName());
        isGameStable();
    }

    private void sendKilledPlayerMessage(Player removedPlayer) {
        KilledPlayerMessage message = new KilledPlayerMessage();
        message.setPlayerName(removedPlayer.getName());
        players.sendMessage(message);
    }

    private void isGameStable() {
        if (gamePlay.getGameStatus().equals(GameResult.GameStable)) players.sendMessage(new DayArrivedMessage());
        else if (gamePlay.getGameStatus().equals(GameResult.MafiaWins)) workflow.gameEnd(gamePlay.getGameStatus());
        else if (gamePlay.getGameStatus().equals(GameResult.MafiaWins)) workflow.gameEnd(gamePlay.getGameStatus());
    }


    @Override
    public void updateMafiaVotes(String playerName, String votedPlayer) {
        view.updateVoteStatus(playerName, votedPlayer);
        gamePlay.poll(votedPlayer);
        isEveryOneVoted();
    }


    private void isEveryOneVoted() {
        if (gamePlay.mafiaPollStatus()) removePlayer(gamePlay.getKilledPlayer());
    }


}
