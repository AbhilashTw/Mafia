package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;


public class NewConnectionListener implements ConnectionListener {

    private final PlayerListController controller;

    public NewConnectionListener(PlayerListController controller) {
        this.controller = controller;
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        controller.addPlayer(new Player(channel, controller));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        throw new RuntimeException("Could not start server", e);
    }

}
