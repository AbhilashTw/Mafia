package views.client;

public interface JoinedPlayersView {
    void displayConnectedPlayers(String[] players);

    void connectedToServer(String serverName, String playerName);

    void connectionClosed();
}
