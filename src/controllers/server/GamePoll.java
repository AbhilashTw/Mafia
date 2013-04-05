package controllers.server;

import entities.Player;
import entities.Players;

import java.util.ArrayList;
import java.util.List;

/**
 * Job: Understands to identify based on maximum vote.
 */
public class GamePoll {
    private List<PlayersBallot> ballot = new ArrayList<PlayersBallot>();
    private int pollCount = 0;

    public void createPlayerBallot(Players players) {
        if (players == null) throw new IllegalArgumentException("PlayerName is null");
        for (Player player : players.getPlayers()) {
            ballot.add(PlayersBallot.createPlayersBallot(player));
        }
    }

    public Player getPlayerVotedOut() {
        PlayersBallot maxVotedPlayer = ballot.get(0);
        for (PlayersBallot players : ballot) {
            if (maxVotedPlayer.getBallotCount() < players.getBallotCount())
                maxVotedPlayer = players;
        }
        return maxVotedPlayer.getPlayer();
    }

    public void poll(String playerName) {
        if (playerName == null) throw new IllegalArgumentException("PlayerName is null");
        for (PlayersBallot playersBallot : ballot) {
            if (playersBallot.isSamePlayer(playerName)) playersBallot.poll();
        }
        pollCount++;
    }

    public int getPollCount() {
        return pollCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePoll)) return false;

        GamePoll gamePoll = (GamePoll) o;

        return pollCount == gamePoll.pollCount && !(ballot != null ? !ballot.equals(gamePoll.ballot) : gamePoll.ballot != null);
    }

    @Override
    public int hashCode() {
        int result = ballot != null ? ballot.hashCode() : 0;
        result = 31 * result + pollCount;
        return result;
    }
}
