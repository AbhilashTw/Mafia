package screens.client;

import controllers.client.JoinServerController;
import screens.controls.MainFrame;
import screens.controls.ImagePanel;
import views.client.JoinServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Job:- Understands connecting client to remote connection.
 */

public class JoinServerScreen implements JoinServerView {
    private static final String BG_IMAGE = "images/joinServerScreen.jpg";
    MainFrame mainFrame;
    private final JoinServerController controller;
    private ImagePanel panel;
    private JLabel serverNameLabel;
    private JLabel playerNameLabel;
    private JTextField serverNameText;
    private JTextField playerNameTextField;
    private JButton connectButton;
    private JPanel displayMessage;


    public JoinServerScreen(MainFrame mainFrame, JoinServerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        panel = mainFrame.createImagePanel(BG_IMAGE);

        serverNameLabel = createLabel("Server Name", 50, 100);
        playerNameLabel = createLabel("Player Name", 50, 200);

        serverNameText = createTextField(150, 140);
        playerNameTextField = createTextField(150, 240);

        connectButton = createButton("Connect", 800, 800);

        panel.add(serverNameLabel);
        panel.add(playerNameLabel);
        panel.add(serverNameText);
        panel.add(playerNameTextField);
        panel.add(connectButton);

        addButtonHandlers();
    }

    private void addButtonHandlers() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.connectToServer();
            }
        });
    }

    @Override
    public String getPlayerName() {
        return playerNameTextField.getText();
    }

    @Override
    public String getServerName() {
        return serverNameText.getText();
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


