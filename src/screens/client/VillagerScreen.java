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

public class VillagerScreen implements VillagerView {

    private static final String BG_IMAGE = "images/VillagerScreen.jpg";
    private final VillagerController controller;
    private IMainFrame mainFrame;

    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList<String>(defaultStatusList);
    private DefaultListModel<String> defaultPlayersList = new DefaultListModel<String>();
    private JList playersList = new JList<String>(defaultPlayersList);

    private JList voteList = new JList<JRadioButton>();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel playerName;
    private JLabel roleLabel;

    private String votedOutPlayer;

    public VillagerScreen(IMainFrame mainFrame, VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(430, 100);

        JLabel villagerLabel = createLabel("Game status", 430, -50);
        JLabel votingPortalLabel = createLabel("Voting Portal", 30, -50);
        JLabel playersListLabel = createLabel("Players", 250, -50);

        playerName = createLabel("Player Name: " + controller.getClientName().toString(), 200, 400);
        roleLabel = createLabel("Role: " + "Villager", 200, 450);

        panel.add(statusList);
        panel.add(playerName);
        panel.add(roleLabel);
        panel.add(playersList);
        panel.add(villagerLabel);
        panel.add(votingPortalLabel);
        panel.add(playersListLabel);
        createPlayersList(200, 100);
        mainFrame.setSize(900, 700);
    }

    //todo: Have a common method for creating list.
    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(400, 450);
        voteList.setLocation(xBound, yBound);
        voteList.setBackground(Color.BLACK);
        voteList.setForeground(Color.WHITE);
        voteList.setFont(new Font("Monospaced", Font.BOLD, 20));
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(450, 350);
        statusList.setLocation(x_bound, y_bound);
        statusList.setBackground(Color.ORANGE);
        statusList.setForeground(Color.BLACK);
        statusList.setFont(new Font("Monospaced", Font.BOLD, 20));
    }

    private void createPlayersList(int x_bound, int y_bound) {
        playersList.setSize(200, 250);
        playersList.setLocation(x_bound, y_bound);
        playersList.setBackground(Color.ORANGE);
        playersList.setForeground(Color.BLACK);
        playersList.setFont(new Font("Monospaced", Font.BOLD, 20));
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
    public void displayPlayers(String[] allPlayersName) {
        defaultPlayersList.removeAllElements();
        for (String s : allPlayersName) {
            defaultPlayersList.addElement(s);
        }
    }

    @Override
    public void display(String[] playersName) {
        JButton confirmButton = createButton(30, 500, "Confirm");
        addListeners(confirmButton);
        panel.add(confirmButton);
        createVoteList(-50, 100);
        panel.add(voteList);
        int x = 80, y = 70;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            customizeButton(x, y, player, button);
            voteList.add(button);
            y += 30;
        }
        panel.repaint();
    }

    private void customizeButton(int x, int y, String player, AbstractButton button) {
        button.setLocation(x, y);
        button.setSize(145, 50);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setVisible(true);
        button.setBackground(Color.ORANGE);
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

        Enumeration<AbstractButton> allButtons = buttonGroup.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }
}
