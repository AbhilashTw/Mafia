package screens.client;

import controllers.client.PlayerDeadController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.PlayerDeadView;

import javax.swing.*;
import java.awt.*;

public class PlayerDeadScreen implements PlayerDeadView {
    private final IMainFrame mainFrame;
    private final PlayerDeadController controller;
    private final ImagePanel panel;
    private static final String BG_IMAGE = "images//KilledScreen.jpg";
    private final JLabel deadLabel;


    public PlayerDeadScreen(IMainFrame mainFrame, PlayerDeadController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        deadLabel = createLabel("You Are Dead....!!!!", 500, 500);
        panel = mainFrame.createImagePanel(BG_IMAGE);

        panel.add(deadLabel);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Comic Sans MS", Font.PLAIN, 28);
        label.setFont(font);
        label.setForeground(Color.RED);
        label.setSize(200, 250);
        label.setLocation(x_bound, y_bound);
        return label;
    }
}
