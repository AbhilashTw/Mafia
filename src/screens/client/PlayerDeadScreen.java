package screens.client;

import controllers.client.PlayerDeadController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.PlayerDeadView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerDeadScreen implements PlayerDeadView {
    private final IMainFrame mainFrame;
    private final PlayerDeadController controller;
    private final ImagePanel panel;
    private JLabel deadLabel;
    private JButton exitButton;
    private DefaultListModel<String> logModel = new DefaultListModel<String>();
    private JList<String> log = new JList<String>(logModel);


    public PlayerDeadScreen(IMainFrame mainFrame, PlayerDeadController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel("images//KilledScreen.jpg");

        exitButton = createButton(10, 600, "Exit");
        createList();

        panel.add(exitButton);
        panel.add(log);
        mainFrame.setSize(400, 700);
        addListeners();
        mainFrame.setSize(400, 400);
    }

    @Override
    public void display(String playerName) {

        deadLabel = new JLabel(playerName + " you are dead...!!!");
        deadLabel.setSize(900, 350);
        deadLabel.setLocation(10, -100);
        deadLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        deadLabel.setForeground(Color.RED);

        panel.add(deadLabel);
        panel.repaint();
    }

    private void createList() {
        log.setSize(300, 450);
        log.setBorder(BorderFactory.createLineBorder(SystemColor.WHITE));
        log.setLocation(10, 100);
        log.setFont(new Font("Monospaced", Font.PLAIN, 14));
        log.setBackground(Color.orange);
    }

    @Override
    public void displayLog(String[] presentStatusLog) {
        for (String s : presentStatusLog) {
            logModel.addElement(s);
        }
    }

    private void addListeners() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToHomeScreen();
            }
        });
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        return button;
    }

}
