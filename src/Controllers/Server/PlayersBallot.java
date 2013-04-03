package controllers.server;

import entities.Player;

/**
 * Job: Understands the a player with regards to his votes.
 */
public class PlayersBallot {
    private final Player player;
    private int totalVotes = 0;

    private PlayersBallot(Player player) {
        this.player = player;
    }

    public static PlayersBallot createPlayersBallot(Player player) {
        if (player == null) throw new IllegalArgumentException("Player is Null");
        return new PlayersBallot(player);
    }

    public int getBallotCount() {
        return totalVotes;
    }

    public void poll() {
        totalVotes++;
    }

    public boolean isSamePlayer(String playerName) {
        return player.getName().equals(playerName);
    }

    public Player getPlayer() {
        return player;
    }
}
