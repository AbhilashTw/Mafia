package views.client;

public interface PlayersConnectedView {
    void displayConnectedPlayers(String[] players);

    void connectedToServer(String serverName, String playerName);
}
