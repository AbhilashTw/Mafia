package views.server;

import entities.Player;

import java.util.List;

public interface ConnectedPlayersView {
    void display(List<Player> players);
}
