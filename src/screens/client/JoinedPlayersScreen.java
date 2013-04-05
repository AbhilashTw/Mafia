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

    public JoinedPlayersScreen(IMainFrame mainFrame, JoinedPlayersController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        ImagePanel panel = mainFrame.createImagePanel(BG_IMAGE);

        playersConnectedLabel = createLabel("Players Joined", 50, -50);
        createList(50, 100);
        exitButton = createButton(800, 800, "Exit");

        panel.add(playersList);
        panel.add(playersConnectedLabel);
        panel.add(exitButton);
        addButtonHandlers();
    }

    private void createList(int x_bound, int y_bound) {
        playersList.setSize(200, 850);
        playersList.setLocation(x_bound, y_bound);
        playersList.setOpaque(false);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
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
    public void displayConnectedPlayers(String[] players) {
        playersDefaultList.removeAllElements();
        for (String player : players) {
            playersDefaultList.addElement(player);
        }
    }

    @Override
    public void connectedToServer(String serverName, String playerName) {
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
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
