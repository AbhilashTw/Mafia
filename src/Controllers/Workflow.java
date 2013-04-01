package controllers;

import channels.messages.ChannelMessage;

import controllers.client.ClientPlayerController;

import controllers.server.Players;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(ClientPlayerController clientPlayerController);

    void start();

    void startVillagerScreen(ChannelMessage message);

    void startMafiaScreen(ChannelMessage message);

    void startGame(Players players);

    void MafiaNightScreen(ChannelMessage message);
}
