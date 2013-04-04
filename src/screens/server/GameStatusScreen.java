package screens.server;

import controllers.server.GameStatusController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.server.GameStatusView;

import javax.swing.*;
import java.awt.*;

public class GameStatusScreen implements GameStatusView {
    private final String BG_IMAGE = "images/status.jpg";
    private final IMainFrame mainFrame;
    private final GameStatusController controller;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList(defaultStatusList);
    private ImagePanel panel;

    public GameStatusScreen(IMainFrame mainFrame, GameStatusController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        panel.add(statusList);
        createList(100, 100);
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(400, 450);
        statusList.setOpaque(true);
        statusList.setLocation(x_bound, y_bound);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        statusList.setFont(f);
    }

    @Override
    public void updateVoteStatus(String playerName, String killedPlayer) {
        defaultStatusList.addElement(playerName + " " + "Voted" + " " + killedPlayer);
    }

    @Override
    public void updatePlayerKilledStatus(String name) {
        defaultStatusList.addElement("Player " + name + " Killed");
    }
}
