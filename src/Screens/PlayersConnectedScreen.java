package Screens;

import Controllers.Client.GameClientController;
import GameMessages.PlayersConnectedMessage;
import Screens.Controls.ImagePanel;
import Views.PlayersConnectedView;

import javax.swing.*;
import java.awt.*;

public class PlayersConnectedScreen implements PlayersConnectedView {
    private static final String BG_IMAGE = "images/joinServerScreen.jpg";
    JFrame playersConnectedFrame;
    ImagePanel playersConnectedScreenImage;
    JLabel playersConnectedLabel;
    DefaultListModel<String> playersDefaultList = new DefaultListModel<String>();
    JList playersList = new JList(playersDefaultList);
    GameClientController client;

    public PlayersConnectedScreen(GameClientController client, JFrame gameFrame) {
        this.client = client;
        playersConnectedFrame = gameFrame;
        playersConnectedScreenImage = new ImagePanel(new ImageIcon(BG_IMAGE).getImage());
        playersConnectedFrame.getContentPane().add(playersConnectedScreenImage);
        playersConnectedFrame.pack();

        playersConnectedLabel = createLabel("Players Joined", 50, -50);
        createList(50, 100);

        playersConnectedScreenImage.add(playersList);
        playersConnectedScreenImage.add(playersConnectedLabel);
        playersConnectedFrame.add(playersConnectedScreenImage);
        playersConnectedScreenImage.setVisible(true);
        playersConnectedFrame.setVisible(true);
        playersConnectedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createList(int x_bound, int y_bound) {
        playersList.setSize(200, 150);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(x_bound, y_bound);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 15);
        playersList.setFont(f);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Comic Sans MS", Font.PLAIN, 28);
        label.setFont(font);
        label.setForeground(Color.RED);
        label.setSize(200, 250);
        label.setLocation(x_bound, y_bound);
        return label;
    }

    @Override
    public void displayConnectedPlayers(PlayersConnectedMessage message) {
        playersDefaultList.removeAllElements();
        for (String player : message.getPlayersConnected()) {
            playersDefaultList.addElement(player);
        }
    }
}
