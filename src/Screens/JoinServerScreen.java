package Screens;

import GameController.Client.GameClient;
import Screens.Controls.ImagePanel;
import Views.JoinServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Job:- Understands connecting a server to client based on the serverNameLabel

public class JoinServerScreen implements JoinServerView {
    private static final String BG_IMAGE = "images/joinServerScreen.jpg";
    JFrame joinServerFrame;
    ImagePanel joinServerScreenImage;
    JLabel serverNameLabel;
    JLabel playerNameLabel;
    JTextField serverNameText;
    JTextField playerNameTextField;
    JButton connectButton;
    JPanel displayMessage;
    GameClient client = new GameClient(1234, this);

    public JoinServerScreen(JFrame gameFrame) {
        joinServerFrame = gameFrame;

        joinServerScreenImage = new ImagePanel(new ImageIcon(BG_IMAGE).getImage());


        serverNameText = new JTextField();
        serverNameText.setSize(100, 30);
        serverNameText.setLocation(200, 200);

        serverNameLabel = new JLabel("Server Name");
        serverNameLabel.setForeground(Color.white);
        serverNameLabel.setSize(100, 100);
        serverNameLabel.setLocation(50, 100);
        Font font = new Font("Monospaced", Font.BOLD, 14);
        serverNameLabel.setFont(font);
        serverNameLabel.setForeground(Color.ORANGE);

        serverNameText = new JTextField();
        serverNameText.setSize(100, 30);
        serverNameText.setLocation(150, 140);

        playerNameLabel = new JLabel("Player Name");
        playerNameLabel.setForeground(Color.white);
        playerNameLabel.setSize(100, 100);
        playerNameLabel.setLocation(50, 200);
        Font playerFont = new Font("Monospaced", Font.BOLD, 14);
        playerNameLabel.setFont(playerFont);
        playerNameLabel.setForeground(Color.ORANGE);


        playerNameTextField = new JTextField();
        playerNameTextField.setSize(100, 30);
        playerNameTextField.setLocation(150, 240);


        connectButton = new JButton("Connect");
        connectButton.setSize(145, 50);
        connectButton.setLocation(800, 800);
        connectButton.setFont(new Font("Verdana", Font.BOLD, 14));
        connectButton.setForeground(Color.ORANGE);
        connectButton.setBackground(Color.BLACK);

        joinServerScreenImage.add(serverNameLabel);
        joinServerScreenImage.add(playerNameLabel);
        joinServerScreenImage.add(serverNameText);
        joinServerScreenImage.add(playerNameTextField);
        joinServerScreenImage.add(connectButton);

        joinServerFrame.getContentPane().add(joinServerScreenImage);
        joinServerFrame.setVisible(true);

    }

    public void connectToServer() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.connectToServer(serverNameText.getText());
            }
        });

    }

    @Override
    public void connectedToServer() {
        PlayersConnectedScreen screen = new PlayersConnectedScreen(client, joinServerFrame);
        client.register(screen);
        screen.display();
        joinServerFrame.setVisible(false);
        displayMessage = new JPanel();
        JOptionPane.showMessageDialog(displayMessage, "Connected to " + serverNameLabel.getText(), "Connected", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void connectionToServerFailed() {
        displayMessage = new JPanel();
        JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to connect", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


