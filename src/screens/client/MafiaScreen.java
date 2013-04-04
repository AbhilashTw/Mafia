package screens.client;

import controllers.client.MafiaController;
import gameMessages.MafiaVotedOutVillagerMessage;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class MafiaScreen implements MafiaView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private final JLabel timerLabel = new JLabel("30");
    private IMainFrame mainFrame;
    private MafiaController controller;
    private ImagePanel panel;

    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList(defaultStatusList);

    private JList voteList = new JList<JRadioButton>();

    private ButtonGroup bg = new ButtonGroup();
    private String votedOutPlayer;

    private Timer timer;

    public MafiaScreen(IMainFrame mainFrame, MafiaController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(900, 100);
        createVoteList(100, 100);
        createTimerLabel();

        panel.add(timerLabel);
        panel.add(statusList);
        panel.add(voteList);
        updateStatus("Your assigned as a Mafia");
    }

    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(200, 650);
        voteList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        voteList.setLocation(xBound, yBound);
        voteList.setBackground(Color.ORANGE);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        voteList.setFont(f);
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
        timerScreen();
        int x = 100, y = 100;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setBackground(Color.ORANGE);
            button.setVisible(true);
            bg.add(button);
            voteList.add(button);
            button.addActionListener(new MyAction());
            y += 80;
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
                    controller.sendMessage(new MafiaVotedOutVillagerMessage(votedOutPlayer));
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

