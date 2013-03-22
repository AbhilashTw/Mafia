package Screens;


import Channels.Server.SocketServer;
import Channels.Server.SocketServerListener;
import Channels.SocketChannel;
import GameController.Server.GameServer;
import Screens.Controls.ImagePanel;
import Views.StartServerView;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

//Job:- Understands to display a window to connect to a server

public class StartServerScreen implements SocketServerListener, StartServerView {
    private static final String BG_COLOR = "images/joinServerScreen.jpg";
    public SocketServer server;
    JFrame startServerFrame;
    JLabel playersJoinedLabel;
    JButton startServerButton;
    ImagePanel startServerScreenImage;
    JPanel displayMessage;
    private ArrayList<GameServer> players = new ArrayList<GameServer>();

    public StartServerScreen(JFrame gameFrame) {
        startServer();
        startServerFrame = gameFrame;
        startServerScreenImage = new ImagePanel(new ImageIcon(BG_COLOR).getImage());
        startServerFrame.getContentPane().add(startServerScreenImage);
        startServerFrame.pack();

        playersJoinedLabel = createLabel("Players Joined", 100, -50);

        startServerButton = createButton("Start Server", 800, 800);

        startServerScreenImage.add(playersJoinedLabel);
        startServerScreenImage.add(startServerButton);
    }

    public void startServer() {
        try {
            server = new SocketServer(1234, this);
            server.start();
        } catch (Exception e) {
            displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to Start Server.  Try Again", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

