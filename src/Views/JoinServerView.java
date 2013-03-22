package Views;

public interface JoinServerView {
    void connectedToServer();

    void connectionToServerFailed();
    String getPlayerName();
}
