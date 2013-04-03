package screens.client;

import controllers.client.VillagerController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.VillagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class VillagerScreen implements VillagerView {

    private static final String BG_IMAGE = "images/VillagerScreen.jpg";
    private final VillagerController controller;
    private IMainFrame mainFrame;
    private final JLabel timerLabel = new JLabel("10");
    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private ButtonGroup bg = new ButtonGroup();
    private JList statusList = new JList(defaultStatusList);
    private Timer timer;

    public VillagerScreen(IMainFrame mainFrame, VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(900, 100);
        createTimerLabel();

        panel.add(timerLabel);
        panel.add(statusList);
        updateStatus("Your Assigned as a villager");
    }

    private void createTimerLabel() {
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setLocation(900, 800);
        timerLabel.setSize(200, 200);
        Font timerFont = new Font("Monospaced", Font.PLAIN, 100);
        timerLabel.setFont(timerFont);
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(600, 450);
        statusList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        statusList.setLocation(x_bound, y_bound);
        statusList.setBackground(Color.ORANGE);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        statusList.setFont(f);
    }

    @Override
    public void display(String[] playersName) {
        updateStatus("Day Arrived \n You can vote now ");
        int x = 100, y = 100;
        for (String player : playersName) {
            JRadioButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setFont(new Font("Times New Roman", 1, 20));
            button.setBackground(Color.ORANGE);
            button.setVisible(true);
            bg.add(button);
            button.addActionListener(new MyAction());
            panel.add(button);
            y += 80;
        }
        timerScreen();
    }

    @Override
    public void updateStatus(String status) {
        defaultStatusList.addElement(status);
    }

    public void timerScreen() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timerLabel.getText());
                if (time > 0)
                    timerLabel.setText(String.valueOf(time - 1));
                else if (time == 0) {
                    timer.stop();
                    disableVoteButtons();
                }
            }
        });
        timer.start();
    }

    private void disableVoteButtons() {
        updateStatus("Your Voting Time Ended");
        Enumeration<AbstractButton> allButtons = bg.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }

    class MyAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            e.getActionCommand();
        }
    }

}
