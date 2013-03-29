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
    private JLabel countDownLabel = new JLabel("", SwingConstants.CENTER);

    public MafiaStartScreen(IMainFrame mainFrame, MafiaStartScreenController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        mafiaLabel = createLabel("Your assigned as a mafia", 100, 450);
        panel.add(mafiaLabel);
        new HurdlerTimer(this).start();
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
    public void display(String[] playersName) {
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

    public void setCountDownLabelText(String text) {
        countDownLabel.setText(text);
    }
}

class HurdlerTimer {
    private MafiaStartScreen mafiaStartScreen;
    private final int MAX_COUNT = 10;
    private static final int TIMER_PERIOD = 1000;
    private int count;

    public HurdlerTimer(MafiaStartScreen mafiaStartScreen) {
        this.mafiaStartScreen = mafiaStartScreen; // initializes the reference to the Welcome class.
        String text = "(" + (MAX_COUNT - count) + ") seconds left";
        mafiaStartScreen.setCountDownLabelText(text);
    }

    public void start() {
        new Timer(TIMER_PERIOD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < MAX_COUNT) {
                    count++;
                    String text = "(" + (MAX_COUNT - count) + ") seconds left";
                    mafiaStartScreen.setCountDownLabelText(text); // uses the reference to Welcome
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }
}

