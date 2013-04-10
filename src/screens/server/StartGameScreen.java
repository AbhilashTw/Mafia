package screens.server;

import channels.server.SocketServer;
import entities.Player;
import controllers.server.StartGameController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.server.StartGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Job:- Understands to display a window to connect to a server
 */

public class StartGameScreen implements StartGameView {
    private static final String BG_IMAGE = "images/startgame.jpg";
    private final StartGameController controller;
    public SocketServer server;
    IMainFrame startServerFrame;
    private JLabel playersJoinedLabel;
    private JLabel minimumPlayersLabel;
    private JButton startServerButton;
    private JButton stopServerButton;
    private ImagePanel panel;
    private DefaultListModel<String> allPlayers = new DefaultListModel<String>();
    private JList<String> playersList = new JList<String>(allPlayers);


    public StartGameScreen(IMainFrame mainFrame, StartGameController controller) {
        startServerFrame = mainFrame;
        this.controller = controller;
        this.controller.bind(this);
        panel = mainFrame.createImagePanel(BG_IMAGE);

        playersJoinedLabel = createLabel("Players Joined", 100, -50);
        minimumPlayersLabel = createLabel("Minimum players : 3", 400, 400);
        startServerButton = createButton("Start Game", 400, 600);
        stopServerButton = createButton("Stop Server", 400, 700);

        createList();

        panel.add(playersJoinedLabel, addGridConstraints(playersJoinedLabel.getAlignmentX(), playersJoinedLabel.getAlignmentY()));
        panel.add(playersList, addGridConstraints(playersList.getAlignmentX() + playersList.getHeight(), playersList.getAlignmentY() + playersList.getWidth()));
        panel.add(startServerButton, addGridConstraints(startServerButton.getX(), startServerButton.getY()));
        panel.add(stopServerButton, addGridConstraints(stopServerButton.getX(), stopServerButton.getY()));
        panel.add(minimumPlayersLabel);
        startServerButton.setEnabled(false);
        setDefaultCloseAction(controller);
    }

    private void setDefaultCloseAction(final StartGameController controller) {
        startServerFrame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.stop();
            }
        });
    }

    private GridBagConstraints addGridConstraints(float xBound, float yBound) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = (int) xBound;
        gbc.gridy = (int) yBound;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(30, 30, 30, 30);
        return gbc;
    }

    @Override
    public void updatePlayers(List<Player> players) {
        allPlayers.removeAllElements();
        for (Player player : players) {
            allPlayers.addElement(player.getName());
        }
        checkMinimumPlayers();
    }

    private void createList() {
        playersList.setSize(200, 550);
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
        return button;
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Monospaced", Font.BOLD, 20);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setSize(250, 250);
        label.setLocation(x_bound, y_bound);
        return label;
    }

    private void checkMinimumPlayers() {
        if (controller.canGameBeStarted()) {
            startServerButton.setEnabled(true);
        }
        addButtonHandlers();
    }


    private void addButtonHandlers() {
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });
        stopServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.stop();
            }
        });
    }
}
