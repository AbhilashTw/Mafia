package screens.client;

import controllers.client.MafiaStartScreenController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaStartScreenView;

import javax.swing.*;
import java.awt.*;

public class MafiaStartScreen implements MafiaStartScreenView {

    IMainFrame mainFrame;
    private final MafiaStartScreenController controller;
    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private ImagePanel panel;
    private JLabel mafiaLabel;

    public MafiaStartScreen(IMainFrame mainFrame, MafiaStartScreenController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        mafiaLabel = createLabel("Your assigned as a mafia", 900, 450);
        panel.add(mafiaLabel);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.white);
        label.setSize(800, 900);
        label.setLocation(x_bound, y_bound);
        Font font = new Font("Monospaced", Font.BOLD, 33);
        label.setFont(font);
        label.setForeground(Color.ORANGE);
        return label;
    }


}
