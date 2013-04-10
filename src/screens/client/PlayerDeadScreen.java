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


    public PlayerDeadScreen(IMainFrame mainFrame, PlayerDeadController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel("images//KilledScreen.jpg");
        exitButton = createButton(100, 800, "Exit");
        panel.add(exitButton);
        addListeners();
    }

    @Override
    public void display(String playerName) {
        deadLabel = new JLabel(playerName + " You are dead...!!!");
        deadLabel.setSize(900, 350);
        deadLabel.setLocation(200, 400);
        deadLabel.setFont(new Font("Monospaced", Font.PLAIN, 28));
        deadLabel.setForeground(Color.RED);
        panel.add(deadLabel);
        panel.repaint();
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
        button.setFont(new Font("Monospaced", Font.BOLD, 14));
        return button;
    }

}
