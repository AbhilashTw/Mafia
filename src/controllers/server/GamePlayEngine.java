package controllers.server;

import entities.Player;

public interface GamePlayEngine {
    void removePlayer(Player killedPlayer);

    void startDay();

    void endGame(GameResult mafiaWins);

    void startNight();

    String[] getLog();
}
