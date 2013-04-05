package controllers.client;


public interface ClientEngine {
    void displayConnectedPlayers(String[] playersConnected);

    void startVillagerScreen();

    void startMafiaScreen();

    void serverClosed();

    void displayMafiaVotingChart(String[] playerNames);

    void displayVillagerVotingChart(String[] playerNames);

    void PlayerKilled(String playerName);

    void showDeadScreen();

    void VillagersWon();

    void MafiasWon();

    void displayMafia(String[] players);
}
