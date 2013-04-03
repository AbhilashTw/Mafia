package views.server;

public interface GameStatusView {
<<<<<<< HEAD
    void updateMafiaVotingStatus(String playerName, String killedPlayer);
    void updateVillagerVotingStatus(String playerName,String killedPlayer);
=======
    void updateVoteStatus(String playerName, String killedPlayer);

    void updatePlayerKilledStatus(String name);
>>>>>>> 6c833b98b4a2c1d79835dd5c7c5fd9adeaaf5146
}
