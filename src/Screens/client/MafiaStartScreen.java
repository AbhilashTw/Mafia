package screens.client;

import controllers.client.MafiaStartScreenController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaStartScreenView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MafiaStartScreen implements MafiaStartScreenView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private final MafiaStartScreenController controller;
    IMainFrame mainFrame;
    private ImagePanel panel;
    private JLabel mafiaLabel;

    private final JLabel timerLabel = new JLabel("60");

    public MafiaStartScreen(IMainFrame mainFrame, MafiaStartScreenController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        mafiaLabel = createLabel("Your assigned as a mafia", 100, 450);

        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBounds(800, 400, 200, 200);
        timerLabel.setSize(145, 50);

        panel.add(timerLabel);
        panel.add(mafiaLabel);

    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        label.setSize(800, 900);
        label.setLocation(x_bound, y_bound);
        Font font = new Font("Monospaced", Font.BOLD, 25);
        label.setFont(font);
        label.setForeground(Color.ORANGE);
        return label;
    }


    @Override
    public void display(String[] playersName) {
        timerScreen();
        ButtonGroup bg = new ButtonGroup();
        int x = 100, y = 100;
        JLabel voteLabel = createLabel("You can vote now", 100, 450);
        panel.add(voteLabel);
        for (String player : playersName) {
            JRadioButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setBackground(Color.ORANGE);
            button.setVisible(true);
            panel.add(button);
            bg.add(button);
            y += 80;
        }
    }

    public void timerScreen() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timerLabel.getText());
                if (time > 0) timerLabel.setText(String.valueOf(time - 1));
            }
        });
        timer.start();
    }
}

