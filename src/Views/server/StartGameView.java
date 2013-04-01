package views.server;

import controllers.server.Player;

import java.util.List;


public interface StartGameView {
    void updatePlayers(List<Player> players);
}
