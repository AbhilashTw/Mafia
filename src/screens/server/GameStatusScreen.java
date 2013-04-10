package screens.server;

import controllers.server.GameStatusController;
import entities.Player;
import entities.Players;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.server.GameStatusView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GameStatusScreen implements GameStatusView {
    private final String BG_IMAGE = "images/status.jpg";
    private final IMainFrame mainFrame;
    private final GameStatusController controller;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList(defaultStatusList);
    private DefaultListModel<String> playersInGame = new DefaultListModel<String>();
    private JList playersList = new JList(playersInGame);
    private ImagePanel panel;
    private JButton quit;
    private JLabel gameStatus;
    private JLabel playersIn;

    public GameStatusScreen(IMainFrame mainFrame, GameStatusController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        panel.add(statusList);
        panel.add(playersList);
        createList(100, 100);
        createPlayersList(600, 100);
        quit = createButton(700, 650, "Quit");
        gameStatus = createLabel("Game Status", 50, 0, 150, 400);
        playersIn = createLabel("Players", 250, 0, 150, 400);
        addDefaultCloseAction();
        addActionListeners();
        panel.add(quit);
        panel.repaint();
    }

    private JLabel createLabel(String labelName, int xBound, int yBound, int xSize, int ySize) {
        JLabel label = new JLabel(labelName);
        label.setFont(new Font("Monospaced", Font.PLAIN, 90));
        label.setForeground(Color.WHITE);
        label.setSize(xSize, ySize);
        label.setLocation(xBound, yBound);
        return label;
    }

    private void addActionListeners() {
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.close();
            }
        });
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        return button;
    }

    private void addDefaultCloseAction() {
        mainFrame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.close();
            }
        });
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(400, 600);
        statusList.setBackground(Color.ORANGE);
        statusList.setForeground(Color.BLACK);
        statusList.setLocation(x_bound, y_bound);
        statusList.setFont(new Font("Monospaced", Font.BOLD, 16));
    }

    private void createPlayersList(int x_bound, int y_bound) {
        playersList.setSize(300, 500);
        playersList.setBackground(Color.ORANGE);
        playersList.setForeground(Color.BLACK);
        playersList.setLocation(x_bound, y_bound);
        playersList.setFont(new Font("Monospaced", Font.BOLD, 14));
    }

    @Override
    public void updateVoteStatus(String playerName, String votedPlayer, String format) {
        defaultStatusList.addElement(format + "" + playerName + "Voted" + " " + votedPlayer);
    }

    @Override
    public String[] getPresentStatusLog() {
        ArrayList<String> statusLog = new ArrayList<String>();
        for (int i = 0; i < defaultStatusList.size(); i++) {
            statusLog.add(defaultStatusList.getElementAt(i));
        }
        return statusLog.toArray(new String[statusLog.size()]);
    }

    @Override
    public void updateVillagerVotes(String name, String playerName, String killedPlayer) {
        defaultStatusList.addElement(name + " " + playerName + "Voted" + " " + killedPlayer);

    }

    @Override
    public void updatePlayerKilledStatus(String name) {
        defaultStatusList.addElement("Player " + name + " Killed");

    }

    @Override
    public void status(String s) {
        defaultStatusList.addElement(s);

    }

    @Override
    public void updatePlayersList(Players players) {
        playersInGame.removeAllElements();
        for (Player player : players.getPlayers()) {
            playersInGame.addElement(player.getName() + " " + "(" + player.getRole() + ")");
        }

    }
}
