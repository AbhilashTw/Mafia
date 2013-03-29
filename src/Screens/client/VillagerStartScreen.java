package screens.client;

import controllers.client.VillagerStartScreenController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.VillagerStartScreenView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillagerStartScreen implements VillagerStartScreenView {

    private static final String BG_IMAGE = "images/homepage.jpg";
    private VillagerStartScreenController controller;
    IMainFrame mainFrame;
    private ImagePanel panel;
    private JLabel villagerLabel;

    public VillagerStartScreen(IMainFrame mainFrame, VillagerStartScreenController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        villagerLabel = createLabel("Your Assigned As a Villager", 100, 500);
        panel.add(villagerLabel);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.white);
        label.setSize(500, 700);
        label.setLocation(x_bound, y_bound);
        Font font = new Font("Monospaced", Font.BOLD, 30);
        label.setFont(font);
        label.setForeground(Color.ORANGE);
        return label;
    }

    @Override
    public void display(String[] playersName) {
        ButtonGroup bg = new ButtonGroup();
        int x = 100, y = 100;
        for (String player : playersName) {
            JRadioButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setFont(new Font("Times New Roman", 1, 20));
            button.setBackground(Color.ORANGE);
            button.setVisible(true);
            panel.add(button);
            bg.add(button);
            y += 80;
        }
    }
}
