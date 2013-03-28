package screens.client;

import controllers.client.MafiaStartScreenController;
import controllers.server.Player;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaStartScreenView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MafiaStartScreen implements MafiaStartScreenView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private final MafiaStartScreenController controller;
    IMainFrame mainFrame;
    private ImagePanel panel;
    private JLabel mafiaLabel;
    private JRadioButton voteButton;
    private List<Player> players;

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

    @Override
    public void showMafiaNightScreen() {
        voteButton = createButton(100, 100, "Vote");
        panel.add(voteButton);
    }

    private JRadioButton createButton(int x_axis, int y_axis, String buttonName) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        return button;
    }
}
