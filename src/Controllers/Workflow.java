package controllers;

import channels.SocketChannel;
import controllers.server.Player;

import java.util.List;

public interface Workflow {
    void startServer();

    void joinServer();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void start();

    void startVillagerScreen();

    void startMafiaScreen();

    void startGame(List<Player> players);

    void mafiaNightArrived();
}
