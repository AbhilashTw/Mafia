package views.client;

public interface VillagerView {
    void display(String[] playersName);

    void updateStatus(String statusMessage);

    void serverClosed();

    void displayPlayers(String[] allPlayersName);
}
