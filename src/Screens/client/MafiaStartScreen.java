package screens.client;

import controllers.client.MafiaStartScreenController;
import controllers.server.Players;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaStartScreenView;

import javax.swing.*;
import java.awt.*;

public class MafiaStartScreen implements MafiaStartScreenView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private final MafiaStartScreenController controller;
    IMainFrame mainFrame;
    private ImagePanel panel;
    private JLabel mafiaLabel;
    private JRadioButton voteButton;


    public MafiaStartScreen(IMainFrame mainFrame, MafiaStartScreenController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        mafiaLabel = createLabel("Your assigned as a mafia", 100, 450);
        panel.add(mafiaLabel);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.white);
        label.setSize(800, 900);
        label.setLocation(x_bound, y_bound);
        Font font = new Font("Monospaced", Font.BOLD, 25);
        label.setFont(font);
        label.setForeground(Color.ORANGE);
        return label;
    }
}
