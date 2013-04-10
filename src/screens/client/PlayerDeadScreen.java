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
        exitButton = createButton(100, 300, "Exit");
        panel.add(exitButton);
        addListeners();
        mainFrame.setSize(400,400);
    }

    @Override
    public void display(String playerName) {
        deadLabel = new JLabel(playerName + " you are dead...!!!");
        deadLabel.setSize(400, 350);
        deadLabel.setLocation(80, 10);
        deadLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        deadLabel.setForeground(Color.WHITE);
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
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        return button;
    }

}
