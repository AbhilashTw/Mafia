package controllers.server;

import entities.Player;

public interface GameEngine {

    void playersUpdated();

    void removePlayer(Player player);

    void updateMafiaVotes(String playerName, String killedPlayer);

    void updateVillagerVotes(String playerName, String killedPlayer);

}
