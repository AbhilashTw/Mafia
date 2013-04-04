package screens.server;

import controllers.server.ConnectedPlayersController;
import entities.Player;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.server.ConnectedPlayersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConnectedPlayersScreen implements ConnectedPlayersView {

    private static final String BG_IMAGE = "images/connected.jpg";
    private final IMainFrame mainFrame;
    private final ConnectedPlayersController controller;
    private ImagePanel panel;
    private DefaultListModel<String> playersNameList = new DefaultListModel<String>();
    private JList playersName = new JList(playersNameList);
    private DefaultListModel<String> playersRoleList = new DefaultListModel<String>();
    private JList playersRole = new JList(playersRoleList);
    private JButton continueButton;


    public ConnectedPlayersScreen(IMainFrame mainFrame, ConnectedPlayersController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        playersName = createList(playersName, 50, 100);
        playersRole = createList(playersRole, 300, 100);
        continueButton = createButton(800, 400, "Continue");
        panel.add(continueButton);
        panel.add(playersName);
        panel.add(playersRole);
        addListener();
    }

    private void addListener() {
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.Continue();
            }
        });
    }

    private JList createList(JList playersList, int x_bound, int y_bound) {
        playersList.setSize(200, 850);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(x_bound, y_bound);
        playersList.setBackground(Color.YELLOW);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        playersList.setFont(f);
        return playersList;
    }

    @Override
    public void display(List<Player> players) {
        for (Player player : players) {
            playersNameList.addElement(player.getName());
            playersRoleList.addElement(player.getRole());
        }
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

}
