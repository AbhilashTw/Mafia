package controllers.server;

public interface GameEngine {

    void playersUpdated();

    void removePlayer(Player player);
}
