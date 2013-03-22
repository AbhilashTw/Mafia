package Windows;

import GameController.Client.GameClient;
import Screens.PlayersConnectedScreen;
import Windows.Controls.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class PlayersConnectedWindow implements PlayersConnectedScreen {
    JFrame playersConnectedFrame;
    ImagePanel playersConnectedScreenImage;
    JLabel playersConnectedLabel;
    DefaultListModel<String> players = new DefaultListModel<String>();
    JList playersList = new JList(players);
    GameClient client;

    public PlayersConnectedWindow(GameClient client) {
        this.client = client;
//        try {
//            message = client.getServerMessage();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        playersConnectedFrame = new JFrame("Players Joined");
        playersConnectedFrame.setBounds(100, 100, 600, 600);
        playersConnectedFrame.setVisible(true);
        playersConnectedFrame.setBackground(Color.BLACK);

        playersConnectedScreenImage = new ImagePanel(new ImageIcon("images/playersConnectedScreen.jpg").getImage());

        playersConnectedFrame.getContentPane().add(playersConnectedScreenImage);
        playersConnectedFrame.pack();

        playersConnectedLabel = new JLabel("Players Joined");
        Font font = new Font("Comic Sans MS", Font.PLAIN, 28);
        playersConnectedLabel.setFont(font);
        playersConnectedLabel.setForeground(Color.RED);
        playersConnectedLabel.setSize(200, 250);
        playersConnectedLabel.setLocation(50, -50);

        playersList.setSize(200, 150);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(50, 100);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 15);
        playersList.setFont(f);

        playersConnectedScreenImage.add(playersList);
        playersConnectedScreenImage.add(playersConnectedLabel);
        playersConnectedFrame.add(playersConnectedScreenImage);
    }

    public void display() {
        String[] clients = "".split("\n");
        for (String s : clients) {
            players.addElement(s);
        }
    }
}