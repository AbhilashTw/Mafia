package controllers.server;

import channels.Messages.ChannelMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Job:- Understands the collection of Player.
 */
public class Players {
    private List<Player> players = new ArrayList<Player>();
    private RoleDecider roleDecider = new RoleDecider(this);

    public void addPlayer(Player player) {
        if (player == null) throw new IllegalArgumentException("Cannot add a null valued player");
        players.add(player);
    }

    public String getPlayersName() {
        String resultName = "";
        for (Player player : players) {
            resultName += player.getName() + "\n";
        }
        return resultName;
    }

    public void sendMessage(ChannelMessage message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    public void removePlayer(Player player) {
        List<Player> newPlayers = new ArrayList<Player>();
        for (Player player1 : players) {
            if (!player1.equals(player)) {
                newPlayers.add(player1);
            }
        }
        players = newPlayers;
    }

    public int getPlayersCount() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void quit() {
        for (Player player : players) {
            player.stop();
        }
    }

    public void assignRoles() {
        roleDecider.assignRoles();
    }

}
