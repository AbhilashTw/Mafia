package Screens;


import Channels.Server.SocketServer;
import Channels.Server.SocketServerListener;
import Channels.SocketChannel;
import GameController.Server.GameServer;
import Screens.Controls.ImagePanel;
import Views.StartServerView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

//Job:- Understands to display a window to connect to a server

public class StartServerScreen implements SocketServerListener, StartServerView {
    public SocketServer server;
    JFrame startServerFrame;
    JLabel playersJoinedLabel;
    JButton startGameButton;
    ImagePanel startServerScreenImage;
    JPanel displayMessage;
    private ArrayList<GameServer> players = new ArrayList<GameServer>();

    public StartServerScreen(JFrame gameFrame) {
        startServer();

        startServerFrame = gameFrame;

        startServerScreenImage = new ImagePanel(new ImageIcon("images/joinServerScreen.jpg").getImage());
        startServerFrame.getContentPane().add(startServerScreenImage);
        startServerFrame.pack();

        //JLabel

        playersJoinedLabel = createLabel("Players Joined");

        //StartButton
        startGameButton = new JButton("Start Game");
        startGameButton.setSize(145, 50);
        startGameButton.setLocation(800, 800);
        startGameButton.setFont(new Font("Verdana", Font.BOLD, 14));
        startGameButton.setForeground(Color.ORANGE);
        startGameButton.setBackground(Color.BLACK);

        //Frame Contents
        startServerScreenImage.add(playersJoinedLabel);
        startServerScreenImage.add(startGameButton);
    }

    private JLabel createLabel(String labelName) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Monospaced", Font.BOLD, 20);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setSize(200, 200);
        label.setLocation(100, -50);
        return label;
    }

    public void startServer() {
        try {
            server = new SocketServer(1234, this);
            server.start();
        } catch (Exception e) {
            displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to Start Server.  Try Again", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void display() throws IOException {
        startServerFrame.setVisible(true);
//        players.addElement(server.getServerName());
//        for (Socket client : server.getClients()) {
//            players.addElement(client.getLocalAddress().getHostName().toString());
//        }
//
//        String result = server.getClientsListToString();
//        server.sendMessage(result);
    }

    @Override
    public void onError(Exception e) {
        throw new RuntimeException("Server Socket Error", e);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        GameServer player = new GameServer(channel);
        players.add(player);
        channel.bind(player);
    }
}

