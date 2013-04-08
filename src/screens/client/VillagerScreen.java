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

        JLabel villagerLabel = createLabel("You are assigned as a Villager", 700, -50);
        JLabel votingPortalLabel = createLabel("Voting Portal", 100, -50);

        panel.add(statusList);
        panel.add(voteList);

        panel.add(villagerLabel);
        panel.add(votingPortalLabel);
    }

    //todo: Have a common method for creating list.

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

    private void addListeners(final JButton confirmButton) {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
                disableVoteButtons(confirmButton);
            }
        });
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
        updateStatus("You can vote now ");
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
        panel.revalidate();
        panel.repaint();
    }


    @Override
    public void serverClosed() {
    }


    private void disableVoteButtons(JButton confirmButton) {
        updateStatus("Your Voting Time Ended");
        confirmButton.setVisible(false);
        voteList.setVisible(false);
        Enumeration<AbstractButton> allButtons = bg.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }
}
