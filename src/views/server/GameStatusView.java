package views.server;

public interface GameStatusView {

    void updateVoteStatus(String playerName, String killedPlayer);

    void updatePlayerKilledStatus(String name);

    void status(String s);
}
