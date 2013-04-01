package screens.client;

import controllers.client.VillagerController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.VillagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillagerScreen implements VillagerView {

    private static final String BG_IMAGE = "images/homepage.jpg";
    private final VillagerController controller;
    private IMainFrame mainFrame;
    private Timer timer;
    private ButtonGroup bg;
    private DefaultListModel<String> statusMessage = new DefaultListModel<String>();
    private ImagePanel panel;
    private JList gameStatus = new JList(statusMessage);
    private JEditorPane timerLabel;
    private JPanel votingPanel;

    public VillagerScreen(IMainFrame mainFrame, VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        panel.add(gameStatus);
        statusMessage.addElement("You have been Assigned as a Villager");
    }

    @Override
    public void display(String[] playersName) {
        votingPanel = new JPanel();
        bg = new ButtonGroup();
        int x = 100, y = 100;

        for (String player : playersName) {
            JRadioButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setFont(new Font("Times New Roman", 1, 20));
            button.setBackground(Color.ORANGE);
            button.setVisible(true);
            votingPanel.add(button);
            bg.add(button);
            y += 80;
        }
        updateStatus("Voting Starts Now");
        timerScreen();
        votingPanel.setVisible(true);
        panel.add(votingPanel);
    }

    @Override
    public void updateStatus(String statusMessage) {
        this.statusMessage.addElement(statusMessage);
    }

    public void timerScreen() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timerLabel.getText());
                if (time > 0) {
                    timerLabel.setText(String.valueOf(time - 1));
                } else
                    timer.stop();
                disableVoting();

            }
        });
        timer.start();
    }

    private void disableVoting() {
        votingPanel.setVisible(false);
    }
}
