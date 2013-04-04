package screens.client;

import controllers.client.VillagerController;
import gameMessages.VillagerVotedOutMafiaMessage;
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
    private final JLabel timerLabel = new JLabel("30");
    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList<String>(defaultStatusList);

    private JList voteList = new JList<JRadioButton>();

    private ButtonGroup bg = new ButtonGroup();
    private Timer timer;

    private String votedOutPlayer;

    public VillagerScreen(IMainFrame mainFrame, VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(900, 100);
        createVoteList(100, 100);
        createTimerLabel();

        panel.add(timerLabel);
        panel.add(statusList);
        panel.add(voteList);
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
        statusList.setLocation(x_bound, y_bound);
        statusList.setOpaque(false);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        statusList.setFont(f);
    }

    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(200, 650);
        voteList.setLocation(xBound, yBound);
        voteList.setOpaque(false);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        voteList.setFont(f);
    }

    @Override
    public void display(String[] playersName) {
        updateStatus("You can vote now ");
        timerScreen();
        int x = 100, y = 100;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            button.setVisible(true);
            bg.add(button);
            voteList.add(button);
            button.addActionListener(new MyAction());
            y += 60;
        }
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
                if (time > 0) timerLabel.setText(String.valueOf(time - 1));
                else if (time == 0) {
                    timer.stop();
                    controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
                    disableVoteButtons();
                }
            }
        });
        timer.start();
    }

    private void disableVoteButtons() {
        updateStatus("Your Voting Time Ended");
        voteList.setVisible(false);
        Enumeration<AbstractButton> allButtons = bg.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }

    class MyAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            votedOutPlayer = e.getActionCommand();
        }
    }

}
