package screens.server;

import controllers.server.GameStatusController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.server.GameStatusView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameStatusScreen implements GameStatusView {
    private final String BG_IMAGE = "images/status.jpg";
    private final IMainFrame mainFrame;
    private final GameStatusController controller;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList(defaultStatusList);
    private ImagePanel panel;
    private JButton quit;

    public GameStatusScreen(IMainFrame mainFrame, GameStatusController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        panel.add(statusList);
        createList(100, 100);
        quit = createButton(700, 700, "Quit");
        addDefaultCloseAction();
        addActionListeners();
        panel.add(quit);
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
        statusList.setSize(400, 800);
        statusList.setBackground(Color.ORANGE);
        statusList.setForeground(Color.BLACK);
        statusList.setLocation(x_bound, y_bound);
        statusList.setFont(new Font("Monospaced", Font.BOLD, 20));
    }

    @Override
    public void updateVoteStatus(String playerName, String killedPlayer) {
        defaultStatusList.addElement(playerName + " " + "Voted" + " " + killedPlayer);
        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void updatePlayerKilledStatus(String name) {

        defaultStatusList.addElement("Player " + name + " Killed");
        panel.revalidate();
        panel.repaint();

    }

    @Override
    public void status(String s) {
        defaultStatusList.addElement(s);
        panel.revalidate();
        panel.repaint();
    }
}
