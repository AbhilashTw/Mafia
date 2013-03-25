package Screens;


import Channels.Server.SocketServer;
import Channels.SocketChannel;
import Controllers.Server.GameServerController;
import Controllers.Server.Player;
import Screens.Controls.ImagePanel;
import Views.StartServerView;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

//Job:- Understands to display a window to connect to a server


public class StartServerScreen implements StartServerView {
    private static final String BG_COLOR = "images/joinServerScreen.jpg";
    public SocketServer server;

    JFrame startServerFrame;
    private final GameServerController controller;
    JLabel playersJoinedLabel;
    JButton startServerButton;
    ImagePanel startServerScreenImage;
    JPanel displayMessage;

    DefaultListModel<String> allPlayers = new DefaultListModel<String>();
    JList<String> playersList = new JList<String>(allPlayers);

    public StartServerScreen(JFrame gameFrame, GameServerController controller) {
        startServerFrame = gameFrame;
        this.controller = controller;
        controller.bind(this);

        startServerScreenImage = new ImagePanel(new ImageIcon("BG_COLOR").getImage());
        startServerFrame.getContentPane().add(startServerScreenImage);
        startServerFrame.pack();

        playersJoinedLabel = createLabel("Players Joined", 100, -50);

        startServerButton = createButton("Start Server", 800, 800);


        playersList.setSize(200, 150);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(50, 100);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 15);
        playersList.setFont(f);

        startServerScreenImage.add(playersJoinedLabel);
        startServerScreenImage.add(startServerButton);
    }



    private JButton createButton(String buttonName, int x_bound, int y_bound) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_bound, y_bound);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
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

    public void display() throws IOException {


        startServerFrame.setVisible(true);
    }

    @Override
    public void updatePlayers(ArrayList<Player> players) {
        for (Player player : players) {
            allPlayers.addElement(player.getName());
        }

    }
}

