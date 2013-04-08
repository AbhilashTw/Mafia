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

    private String votedOutPlayer;

    public VillagerScreen(IMainFrame mainFrame, VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(700, 100);
        createVoteList(100, 100);
        createTimerLabel();

        JLabel villagerLabel = createLabel("You are assigned as a Villager", 700, -50);
        JLabel votingPortalLabel = createLabel("Voting Portal", 100, -50);

        panel.add(timerLabel);
        panel.add(statusList);
        panel.add(voteList);


        panel.add(villagerLabel);
        panel.add(votingPortalLabel);

    }

    //todo: Have a common method for creating list.
    private void createTimerLabel() {
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setLocation(900, 800);
        timerLabel.setSize(200, 200);
        Font timerFont = new Font("Monospaced", Font.PLAIN, 100);
        timerLabel.setFont(timerFont);
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(450, 450);
        statusList.setLocation(x_bound, y_bound);
        statusList.setFont(new Font("Monospaced", Font.PLAIN, 20));
        statusList.setBackground(Color.ORANGE);
        statusList.setForeground(Color.BLACK);
    }

    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(200, 650);
        voteList.setLocation(xBound, yBound);
        voteList.setFont(new Font("Monospaced", Font.PLAIN, 20));
        voteList.setBackground(Color.BLACK);
        voteList.setForeground(Color.WHITE);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Monospaced", Font.BOLD, 16);
        label.setFont(font);
        label.setForeground(Color.RED);
        label.setBackground(Color.BLACK);
        label.setSize(300, 250);
        label.setLocation(x_bound, y_bound);
        return label;
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
            button.setBackground(Color.ORANGE);
            button.setForeground(Color.black);
            bg.add(button);


            voteList.add(button);
            if (controller.getClientName().equals(player)) {
                button.setSelected(true);
                votedOutPlayer = controller.getClientName();
            }
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    votedOutPlayer = e.getActionCommand();
                }
            });
            y += 60;
        }
    }

    @Override
    public void updateStatus(String status) {
        defaultStatusList.addElement(status);
        panel.repaint();
    }

    public void timerScreen() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timerLabel.getText());
                if (time > 0) timerLabel.setText(String.valueOf(time - 1));
                else if (time == 0) {
                    controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
                    disableVoteButtons();
                    ((Timer) e.getSource()).stop();

                }
            }
        });
        timer.start();
    }

    @Override
    public void serverClosed() {
        JOptionPane optionPane = new JOptionPane("Server Closed", JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    private void disableVoteButtons() {
        panel.repaint();
        updateStatus("Your Voting Time Ended");
        voteList.setVisible(false);
        Enumeration<AbstractButton> allButtons = bg.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }
}
