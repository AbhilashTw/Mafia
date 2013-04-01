package controllers.client;

public interface ClientEngine {
    void displayConnectedPlayers(String[] playersConnected);

    void startVillagerScreen();

    void startMafiaScreen();

    void ServerClosed();

    void displayMafiaVotingChart(String[] playerNames);
}
