package screens.client;

import controllers.client.JoinServerController;
import screens.controls.IMainFrame;
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
    private final JoinServerController controller;
    private final JLabel serverNameLabel;
    private final JLabel playerNameLabel;
    private final JTextField serverNameText;
    private final JTextField playerNameTextField;
    private final JButton connectButton;
    public IMainFrame mainFrame;
    private JPanel displayMessage;
    private boolean sentConnection = false;


    public JoinServerScreen(IMainFrame mainFrame, JoinServerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        ImagePanel panel = mainFrame.createImagePanel(BG_IMAGE);
        serverNameLabel = createLabel("Server Name", 50, 100);
        playerNameLabel = createLabel("Player Name", 50, 200);
        serverNameText = createTextField(150, 140);
        playerNameTextField = createTextField(150, 240);

        //todo: remove setText before pushing into production
        serverNameText.setText("localhost");
        connectButton = createButton("Connect", 300, 300);

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
                //todo :Work on if else
                if (serverNameText.getText().equals("")) {
                    displayMessage = new JPanel();
                    JOptionPane.showMessageDialog(displayMessage, "Invalid Server Name", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!sentConnection) {
                        controller.connectToServer();
                        sentConnection = true;
                    }
                }
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
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        return button;
    }

    @Override
    public void connectionToServerFailed() {
        displayMessage = new JPanel();
        sentConnection = false;
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


