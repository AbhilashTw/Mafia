package screens.server;

import channels.Server.SocketServer;
import controllers.client.PlayersListController;
import controllers.server.GameServerController;
import controllers.server.Player;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.server.GameServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Job:- Understands to display a window to connect to a server
 */

public class GameServerScreen implements GameServerView {
    private static final String BG_IMAGE = "images/joinServerScreen.jpg";
    private final GameServerController controller;
    public SocketServer server;
    IMainFrame startServerFrame;
    PlayersListController playersListController;
    private JLabel playersJoinedLabel;
    private JButton startServerButton;
    private JButton stopServerButton;
    private ImagePanel panel;
    private DefaultListModel<String> allPlayers = new DefaultListModel<String>();
    private JList<String> playersList = new JList<String>(allPlayers);

    public GameServerScreen(IMainFrame mainFrame, GameServerController controller) {
        startServerFrame = mainFrame;
        this.controller = controller;
        this.controller.bind(this);
        panel = mainFrame.createImagePanel(BG_IMAGE);

        playersJoinedLabel = createLabel("Players Joined", 100, -50);
        startServerButton = createButton("Start Game", 800, 800);
        stopServerButton = createButton("Stop Server", 800, 900);
        createList();

        panel.add(playersJoinedLabel);
        panel.add(startServerButton);
        panel.add(stopServerButton);
        panel.add(playersList);
        addButtonHandlers();
    }

    @Override
    public void updatePlayers(List<Player> players) {
        allPlayers.removeAllElements();
        for (Player player : players) {
            allPlayers.addElement(player.getName());
        }
    }

    private void createList() {
        playersList.setSize(200, 850);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.WHITE));
        playersList.setLocation(100, 100);
        Font f = new Font("Monospaced", Font.PLAIN, 25);
        playersList.setFont(f);
        playersList.setBackground(Color.orange);
    }

    private JButton createButton(String buttonName, int x_bound, int y_bound) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_bound, y_bound);
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
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

    private void addButtonHandlers() {
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.canGameBeStarted()) {

                } else {
                    JOptionPane optionPane = new JOptionPane("Cannot Start Game," +
                            "Required Minimum 3 players to Start Game ", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error Message");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }
        });
        stopServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.stop();
            }
        });
    }
}
