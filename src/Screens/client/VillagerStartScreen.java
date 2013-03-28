package screens.client;

import controllers.client.VillagerStartScreenController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.VillagerStartScreenView;

import javax.swing.*;
import java.awt.*;

public class VillagerStartScreen implements VillagerStartScreenView {

    private static final String BG_IMAGE = "images/villagerStartScreen.jpg";
    private final VillagerStartScreenController controller;
    IMainFrame mainFrame;
    private ImagePanel panel;
    private JLabel villagerLabel;

    public VillagerStartScreen(IMainFrame mainFrame, VillagerStartScreenController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        villagerLabel = createLabel("Your assigned as a villager", 100, 500);
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


}
