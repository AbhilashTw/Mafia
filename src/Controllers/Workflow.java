package controllers;

import channels.Messages.ChannelMessage;
import controllers.client.ClientPlayer;
import controllers.server.Players;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(ClientPlayer clientPlayer);

    void start();

    void startVillagerScreen(ChannelMessage message);

    void startMafiaScreen(ChannelMessage message);

    void startGame(Players players);

}
