package views.server;

import controllers.server.Player;

import java.util.List;


public interface GameServerView {
    void updatePlayers(List<Player> players);
}
