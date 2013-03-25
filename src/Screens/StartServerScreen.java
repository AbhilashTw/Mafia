package Screens;

import Channels.Server.SocketServer;
import Controllers.Server.GameServerController;
import Controllers.Server.Player;
import Screens.Controls.ImagePanel;
import Views.StartServerView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Job:- Understands to display a window to connect to a server
 */

public class StartServerScreen implements StartServerView {
    private static final String BG_COLOR = "images/joinServerScreen.jpg";
    private final GameServerController controller;
    public SocketServer server;
    JFrame startServerFrame;
    JLabel playersJoinedLabel;
    JButton startServerButton;
    ImagePanel startServerScreenImage;
    DefaultListModel<String> allPlayers = new DefaultListModel<String>();
    JList<String> playersList = new JList<String>(allPlayers);

    public StartServerScreen(JFrame gameFrame, GameServerController controller) {
        startServerFrame = gameFrame;
        this.controller = controller;
        this.controller.bind(this);

        startServerScreenImage = new ImagePanel(new ImageIcon(BG_COLOR).getImage());
        startServerFrame.getContentPane().add(startServerScreenImage);
        startServerFrame.pack();

        playersJoinedLabel = createLabel("Players Joined", 100, -50);
        startServerButton = createButton("Start Game", 800, 800);
        createList();

        startServerScreenImage.add(playersJoinedLabel);
        startServerScreenImage.add(startServerButton);
        startServerScreenImage.add(playersList);
    }

    public void display() throws IOException {
        startServerFrame.setVisible(true);
    }

    @Override
    public void updatePlayers(ArrayList<Player> players) {
        allPlayers.removeAllElements();
        for (Player player : players) {
            allPlayers.addElement(player.getName());
        }
    }

    private void createList() {
        playersList.setSize(200, 850);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.WHITE));
        playersList.setLocation(100, 100);
        Font f = new Font("Monospaced", Font.PLAIN, 25);
        playersList.setFont(f);
        playersList.setBackground(Color.orange);
    }

    private JButton createButton(String buttonName, int x_bound, int y_bound) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_bound, y_bound);
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        return button;
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Monospaced", Font.BOLD, 20);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setSize(200, 200);
        label.setLocation(x_bound, y_bound);
        return label;
    }
}

