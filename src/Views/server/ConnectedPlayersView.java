package views.server;

import controllers.server.Player;

import java.util.List;

public interface ConnectedPlayersView {
    void display(List<Player> players);
}
