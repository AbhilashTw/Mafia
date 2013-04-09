package screens.client;

import controllers.client.JoinedPlayersController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.JoinedPlayersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinedPlayersScreen implements JoinedPlayersView {
    private static final String BG_IMAGE = "images/joinServerScreen.jpg";
    private final JoinedPlayersController controller;
    IMainFrame mainFrame;
    private JLabel playersConnectedLabel;
    private JButton exitButton;
    private DefaultListModel<String> playersDefaultList = new DefaultListModel<String>();
    private JList playersList = new JList<String>(playersDefaultList);
    private JLabel gameInfoLabel;
    ImagePanel panel;

    public JoinedPlayersScreen(IMainFrame mainFrame, JoinedPlayersController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                playersConnectedLabel = createLabel("Players Joined", 50, -50, 200, 250);
                gameInfoLabel = createLabel("Wait for the game to start", 400, 400, 350, 400);
                createList(50, 100);

                exitButton = createButton(300, 300, "Exit");
                panel.add(playersList);
                panel.add(playersConnectedLabel);
                panel.add(exitButton);
                panel.add(gameInfoLabel);
                addButtonHandlers();
            }
        });
    }

    private void createList(int x_bound, int y_bound) {
        playersList.setSize(200, 850);
        playersList.setLocation(x_bound, y_bound);
        playersList.setBackground(Color.ORANGE);
        playersList.setForeground(Color.black);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        playersList.setFont(f);
    }

    private JLabel createLabel(String labelName, int xBound, int yBound, int xSize, int ySize) {
        JLabel label = new JLabel(labelName);
        label.setFont(new Font("Monospaced", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        label.setSize(xSize, ySize);
        label.setLocation(xBound, yBound);
        return label;
    }

    @Override
    public void displayConnectedPlayers(String[] players) {
        playersDefaultList.removeAllElements();
        for (String player : players) {
            playersDefaultList.addElement(player);
        }
    }

    @Override
    public void connectedToServer(String serverName, String playerName) {
        JLabel connectedStatus = new JLabel("Connected to " + serverName + " as " + playerName);
        connectedStatus.setSize(400, 400);
        connectedStatus.setLocation(300, 400);
        connectedStatus.setForeground(Color.WHITE);
        connectedStatus.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(connectedStatus);
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        return button;
    }

    private void addButtonHandlers() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(null, "Do you really want to quit?", "", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    controller.goToHomeScreen();
                }
            }
        });
    }

    @Override
    public void connectionClosed() {
        JOptionPane optionPane = new JOptionPane("Server Closed", JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
