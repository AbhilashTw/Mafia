package entities;

import channels.messages.ChannelMessage;
import controllers.server.GameEngine;
import controllers.server.RoleDecider;

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

    public String[] getAllPlayersName() {
        ArrayList<String> names = new ArrayList<String>();
        for (Player player : players) {
            names.add(player.getName());
        }
        return names.toArray(new String[names.size()]);
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
        player.stop();
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

    public void bindEngine(GameEngine engine) {
        for (Player player : players) {
            player.setGameEngine(engine);
        }
    }

}
