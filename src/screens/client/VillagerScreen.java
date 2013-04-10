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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import java.util.Timer;
import java.util.TimerTask;

public class VillagerScreen implements VillagerView {

    private static final String BG_IMAGE = "images/VillagerScreen.jpg";
    private final VillagerController controller;
    private IMainFrame mainFrame;

    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList<String>(defaultStatusList);
    private JList voteList = new JList<JRadioButton>();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel playerName;

    private String votedOutPlayer;

    public VillagerScreen(IMainFrame mainFrame, VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(700, 100);

        JLabel villagerLabel = createLabel("You are assigned as a Villager", 700, -50);
        JLabel votingPortalLabel = createLabel("Voting Portal", 100, -50);
        playerName = createLabel("Player Name: " + controller.getClientName().toString(), 400, 600);

        panel.add(statusList);
        panel.add(playerName);
        panel.add(villagerLabel);
        panel.add(votingPortalLabel);
    }

    //todo: Have a common method for creating list.

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(450, 450);
        statusList.setLocation(x_bound, y_bound);
        statusList.setFont(new Font("Monospaced", Font.BOLD, 20));
        statusList.setBackground(Color.ORANGE);
        statusList.setForeground(Color.BLACK);
    }

    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(200, 650);
        voteList.setLocation(xBound, yBound);
        voteList.setFont(new Font("Monospaced", Font.BOLD, 20));
        voteList.setBackground(Color.BLACK);
        voteList.setForeground(Color.WHITE);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Monospaced", Font.BOLD, 20);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setSize(300, 250);
        label.setLocation(x_bound, y_bound);
        return label;
    }

    private void addListeners(final JButton confirmButton) {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        sendMessage(confirmButton);

            }
        });
    }

    private void sendMessage(JButton confirmButton) {
        controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
        disableVoteButtons(confirmButton);
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        return button;
    }

    @Override
    public void display(String[] playersName) {

        JButton confirmButton = createButton(50, 700, "Confirm");
        addListeners(confirmButton);
        panel.add(confirmButton);

        createVoteList(100, 100);
        panel.add(voteList);

        updateStatus("You can vote now ");
        int x = 100, y = 100;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            customizeButton(x, y, player, button);
            voteList.add(button);
            y += 60;
        }
    }

    private void customizeButton(int x, int y, String player, AbstractButton button) {
        button.setLocation(x, y);
        button.setSize(145, 50);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        button.setVisible(true);
        button.setBackground(Color.ORANGE);
        button.setForeground(Color.black);
        buttonGroup.add(button);
        setDefaultSelect(player, button);
        addActionListener(button);
    }

    private void addActionListener(AbstractButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                votedOutPlayer = e.getActionCommand();
            }
        });
    }

    private void setDefaultSelect(String player, AbstractButton button) {
        if (controller.getClientName().equals(player)) {
            button.setSelected(true);
            votedOutPlayer = controller.getClientName();
        }
    }

    @Override
    public void updateStatus(String status) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        defaultStatusList.addElement(format.format(cal.getTime()) + " " + status);
        panel.revalidate();
        panel.repaint();
    }


    @Override
    public void serverClosed() {
//        JOptionPane optionPane = new JOptionPane("Server Closed ", JOptionPane.ERROR_MESSAGE);
//        JDialog dialog = optionPane.createDialog("Error Message");
//        dialog.setAlwaysOnTop(true);
//        dialog.setVisible(true);
    }

    private void disableVoteButtons(JButton confirmButton) {
        voteList.removeAll();
        confirmButton.setVisible(false);

        updateStatus("Your Voting Time Ended");
        Enumeration<AbstractButton> allButtons = buttonGroup.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }
}
