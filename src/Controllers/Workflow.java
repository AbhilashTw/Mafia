package controllers;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import controllers.server.Players;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void start();

    void startVillagerScreen(ChannelMessage message);

    void startMafiaScreen(ChannelMessage message);

    void startGame(Players players);

}
