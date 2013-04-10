package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import entities.Player;

import javax.swing.*;


public class NewConnectionListener implements ConnectionListener {

    private final StartGameController controller;


    public NewConnectionListener(StartGameController controller) {
        this.controller = controller;

    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        controller.addPlayer(new Player(channel, controller));
    }

    @Override
    public void connectionSuccessful() {
        controller.serverActive(true);
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        JOptionPane optionPane = new JOptionPane("Server Cant be Started", JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        controller.goToHomeScreen();
    }

}
