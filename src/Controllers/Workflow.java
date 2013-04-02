package controllers;


import controllers.client.ClientPlayerController;
import controllers.server.Players;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(ClientPlayerController clientPlayerController);

    void start();

    void startVillagerScreen();

    void startMafiaScreen();

    void startGame(Players players);

    void showGameStatus();
}
