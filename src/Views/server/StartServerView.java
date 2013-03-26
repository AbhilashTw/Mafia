package views.server;

import controllers.server.Player;

import java.util.ArrayList;
import java.util.List;

public interface StartServerView {
    void updatePlayers(List<Player> players);
}
