package views.server;

import entities.Players;

public interface GameStatusView {

    void updateVillagerVotes(String name, String playerName, String killedPlayer);

    void updatePlayerKilledStatus(String name);

    void status(String s);

    void updatePlayersList(Players players);

    void updateVoteStatus(String playerName, String votedPlayer, String format);

    String[] getPresentStatusLog();
}
