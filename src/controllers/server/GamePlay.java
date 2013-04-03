package controllers.server;

import entities.Player;
import entities.Players;
import entities.Role;
import gameMessages.NightArrivedMessage;

public class GamePlay {
    private Players players;
    private GamePoll poll;

    public void createPlayersPoll(GamePoll poll, Players players) {
        this.poll = poll;
        this.players = players;
        poll.createPlayerBallot(this.players);
    }

    public int getPollCount() {
        return poll.getPollCount();
    }

    public void poll(String playerName) {
        poll.poll(playerName);
    }

    public boolean mafiaPollStatus() {
        return getMafiaCount() == poll.getPollCount();
    }

    private int getMafiaCount() {
        int count = 0;
        for (Player player : players.getPlayers()) {
            if (player.getRole().equals(Role.Mafia.toString())) count++;
        }
        return count;
    }

    public Player getKilledPlayer() {
        return poll.getPlayerVotedOut();
    }

    private int getVillagerCount() {
        int count = 0;
        for (Player player : players.getPlayers()) {
            if (player.getRole().equals(Role.Villager.toString())) count++;
        }
        return count;
    }

    public GameResult getGameStatus() {
        if (getMafiaCount() == 0) return GameResult.VillagerWins;
        else if (getVillagerCount() <= getMafiaCount()) return GameResult.MafiaWins;
        return GameResult.GameStable;
    }

}
