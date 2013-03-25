package views.server;

import controllers.server.Player;

import java.util.ArrayList;

public interface GameServerView {
    void updatePlayers(ArrayList<Player> players);
}
