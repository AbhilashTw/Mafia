package Views;

import Controllers.Server.Player;

import java.util.ArrayList;

public interface StartServerView {
    void updatePlayers(ArrayList<Player> players);
}
