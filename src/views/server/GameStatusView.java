package views.server;

public interface GameStatusView {
    void updateMafiaVotingStatus(String playerName, String killedPlayer);
    void updateVillagerVotingStatus(String playerName,String killedPlayer);
}
