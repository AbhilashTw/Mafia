package Screens;


import Channels.Server.SocketServer;
import Channels.SocketChannel;
import Controllers.Server.GameServerController;
import Controllers.Server.Player;
import Screens.Controls.ImagePanel;
import Views.StartServerView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

//Job:- Understands to display a window to connect to a server

public class StartServerScreen implements StartServerView {

    JFrame startServerFrame;
    private final GameServerController controller;
    JLabel playersJoinedLabel;
    JButton startGameButton;
    ImagePanel startServerScreenImage;
    JPanel displayMessage;

    DefaultListModel<String> allPlayers = new DefaultListModel<String>();
    JList<String> playersList = new JList<String>(allPlayers);

    public StartServerScreen(JFrame gameFrame, GameServerController controller) {
        startServerFrame = gameFrame;
        this.controller = controller;
        controller.bind(this);

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

        //List of players

        playersList.setSize(200, 150);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(50, 100);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 15);
        playersList.setFont(f);

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
    /*
    * displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to Start Server.  Try Again", "Error",
                    JOptionPane.ERROR_MESSAGE);
    * */

    public void display() {
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
    public void updatePlayers(ArrayList<Player> players) {
        for (Player player : players) {
            allPlayers.addElement(player.getName());
        }

    }
}

