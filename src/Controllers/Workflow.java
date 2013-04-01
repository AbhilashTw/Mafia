package controllers;


import controllers.client.ClientPlayer;
import controllers.server.Players;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(ClientPlayer clientPlayer);

    void start();

    void startVillagerScreen();

    void startMafiaScreen();

    void startGame(Players players);

    void showGameStatus();
}
