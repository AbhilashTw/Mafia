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
        gamePlay.createPlayersPoll(new GamePoll(), players);
        message.setPlayersName(players.getAllPlayersName());
        players.sendMessage(message);
    }

    @Override
    public void playersUpdated() {
    }

    @Override
    public void removePlayer(Player removedPlayer, GameStatus status) {
        removedPlayer.sendMessage(new KilledMessage());
        players.removePlayer(removedPlayer);
        sendKilledPlayerMessage(removedPlayer);
        view.updatePlayerKilledStatus(removedPlayer.getName());
        isGameStable(status);
    }

    private void sendKilledPlayerMessage(Player removedPlayer) {
        KilledPlayerMessage message = new KilledPlayerMessage();
        message.setPlayerName(removedPlayer.getName());
        players.sendMessage(message);
    }

    private void isGameStable(GameStatus status) {
        if ((status.equals(GameStatus.NIGHT) && gamePlay.getGameStatus().equals(GameResult.GameStable)))
            sendDayArrivedMessage();
        else if ((status.equals(GameStatus.DAY) && gamePlay.getGameStatus().equals(GameResult.GameStable)))
            sendNightArrivedMessage();
        else if (gamePlay.getGameStatus().equals(GameResult.MafiaWins)) workflow.gameEnd(gamePlay.getGameStatus());
        else if (gamePlay.getGameStatus().equals(GameResult.MafiaWins)) workflow.gameEnd(gamePlay.getGameStatus());
    }

    private void sendDayArrivedMessage() {
        DayArrivedMessage message = new DayArrivedMessage();
        gamePlay.createPlayersPoll(new GamePoll(), players);
        message.setPlayersName(players.getAllPlayersName());
        players.sendMessage(message);
    }

    @Override
    public void updateMafiaVotes(String playerName, String votedPlayer) {
        view.updateVoteStatus(playerName, votedPlayer);
        gamePlay.poll(votedPlayer);
        isAllMafiaVoted();
    }

    private void isAllMafiaVoted() {
        if (gamePlay.mafiaPollStatus()) removePlayer(gamePlay.getKilledPlayer(), GameStatus.NIGHT);
    }

    @Override
    public void updateVillagerVotes(String playerName, String killedPlayer) {
        view.updateVoteStatus(playerName, killedPlayer);
        gamePlay.poll(killedPlayer);
        isAllVillagersVoted();
    }

    private void isAllVillagersVoted() {
        if (gamePlay.villagerPollStatus()) removePlayer(gamePlay.getKilledPlayer(), GameStatus.DAY);
        if (gamePlay.getGameStatus().equals(GameResult.GameStable)) start();
    }

}
