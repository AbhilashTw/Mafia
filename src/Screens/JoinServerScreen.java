package Screens;

import Controllers.Client.GameClientController;
import Screens.Controls.ImagePanel;
import Views.JoinServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Job:- Understands connecting client to remote connection.
 */

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
    GameClientController client = new GameClientController(1234, this);

    public JoinServerScreen(JFrame gameFrame) {
        joinServerFrame = gameFrame;
        joinServerScreenImage = new ImagePanel(new ImageIcon(BG_IMAGE).getImage());

        serverNameText = createTextField(200, 150);
        serverNameLabel = createLabel("Server Name", 50, 100);
        playerNameTextField = createTextField(150, 240);
        playerNameLabel = createLabel("Player Name", 50, 200);
        connectButton = createButton("Connect", 800, 800);

        joinServerScreenImage.add(serverNameLabel);
        joinServerScreenImage.add(playerNameLabel);
        joinServerScreenImage.add(serverNameText);
        joinServerScreenImage.add(playerNameTextField);
        joinServerScreenImage.add(connectButton);

        joinServerFrame.getContentPane().add(joinServerScreenImage);
        joinServerFrame.setVisible(true);

    }

    @Override
    public void connectedToServer() {
        PlayersConnectedScreen screen = new PlayersConnectedScreen(client, joinServerFrame);
        client.register(screen);
        joinServerScreenImage.setVisible(false);
        displayMessage = new JPanel();
        JOptionPane.showMessageDialog(displayMessage, "Connected to " + serverNameText.getText(), "Connected", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public String getPlayerName() {
        return playerNameTextField.getText();
    }

    public void connectToServer() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.connectToServer(serverNameText.getText());
            }
        });

    }

    private JButton createButton(String buttonLabel, int x_bound, int y_bound) {
        JButton button = new JButton(buttonLabel);
        button.setSize(145, 50);
        button.setLocation(x_bound, y_bound);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        return button;
    }

    @Override
    public void connectionToServerFailed() {
        displayMessage = new JPanel();
        JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to connect", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.white);
        label.setSize(100, 100);
        label.setLocation(x_bound, y_bound);
        Font font = new Font("Monospaced", Font.BOLD, 14);
        label.setFont(font);
        label.setForeground(Color.ORANGE);
        return label;
    }

    private JTextField createTextField(int x_bound, int y_Bound) {
        JTextField JTextField = new JTextField();
        JTextField.setSize(100, 30);
        JTextField.setLocation(x_bound, y_Bound);
        return JTextField;
    }
}


