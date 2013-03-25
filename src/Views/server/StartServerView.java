package views.server;

import controllers.server.Player;

import java.util.ArrayList;

public interface StartServerView {
    void updatePlayers(ArrayList<Player> players);
}
