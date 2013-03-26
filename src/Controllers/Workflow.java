package controllers;

import channels.SocketChannel;

public interface Workflow {
    void startServer();
    void joinServer();
    void connectedToServer(SocketChannel channel, String serverName, String playerName);
    void start();
}
