package controllers.server;

import entities.Player;
import entities.Players;
import gameMessages.KilledMessage;
import gameMessages.KilledPlayerMessage;
import gameMessages.KnowMafiaMessage;
import gameMessages.NightArrivedMessage;

public class NightController {
    private final Players players;
    private final GamePlay play;
    private final GamePlayEngine engine;

    public NightController(Players players, GamePlay play, GamePlayEngine engine) {
        this.players = players;
        this.play = play;
        this.engine = engine;
    }

    public void start() {
        sendNightArrivedMessage();
        sendAllMafiaNames();
        play.createPlayersPoll(new GamePoll(), players);
    }

    private void sendAllMafiaNames() {
        KnowMafiaMessage message = new KnowMafiaMessage();
        message.setPlayers(players.getMafiaPlayers());
        players.sendMessageToAllMafia(message);
    }

    private void sendNightArrivedMessage() {
        NightArrivedMessage message = new NightArrivedMessage();
        message.setPlayersName(players.getAllPlayersName());
        players.sendMessage(message);
    }

    public void pollPlayer(String votedPlayer) {
        play.poll(votedPlayer);
        isEveryOneVoted();
    }

    private void isEveryOneVoted() {
        if (play.nightPollStatus()) removePlayer();
    }

    private void removePlayer() {
        Player deadPlayer = play.getKilledPlayer();

        sendKilledMessage(deadPlayer);

        engine.removePlayer(deadPlayer);

        sendKilledPlayerMessage(deadPlayer);

        isGameStable();
    }

    private void sendKilledMessage(Player deadPlayer) {
        deadPlayer.sendMessage(new KilledMessage());

    }

    private void sendKilledPlayerMessage(Player removedPlayer) {
        KilledPlayerMessage message = new KilledPlayerMessage();
        message.setPlayerName(removedPlayer.getName());
        players.sendMessage(message);
    }

    private void isGameStable() {
        if (play.getGameStatus().equals(GameResult.GameStable)) engine.startDay();
        if (play.getGameStatus().equals(GameResult.MafiaWins)) engine.endGame(GameResult.MafiaWins);
        if (play.getGameStatus().equals(GameResult.VillagerWins)) engine.endGame(GameResult.VillagerWins);
    }
}
