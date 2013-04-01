package controllers.client;


public interface ClientEngine {
    void displayConnectedPlayers(String[] playersConnected);

    void startVillagerScreen();

    void startMafiaScreen();

    void serverClosed();

    void displayMafiaVotingChart(String[] playerNames);

}
