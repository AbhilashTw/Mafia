package screens.client;

import controllers.client.MafiaController;
import controllers.server.GameStatus;
import gameMessages.MafiaVotedOutVillagerMessage;
import gameMessages.VillagerVotedOutMafiaMessage;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

public class MafiaScreen implements MafiaView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private IMainFrame mainFrame;
    private MafiaController controller;
    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList<String>(defaultStatusList);

    private DefaultListModel<String> defaultPlayersList = new DefaultListModel<String>();
    private JList playersList = new JList<String>(defaultPlayersList);

    private JLabel playerName;
    private JLabel roleLabel;

    private JList voteList = new JList<JRadioButton>();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private String votedOutPlayer;

    private GameStatus status;


    public MafiaScreen(IMainFrame mainFrame, MafiaController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(430, 100);

        JLabel mafiaLabel = createLabel("Game status", 430, -50);
        JLabel votingPortalLabel = createLabel("Voting Portal", 30, -50);
        JLabel playersListLabel = createLabel("Mafians", 250, -50);

        playerName = createLabel("Player Name: " + controller.getClientName(), 200, 400);
        roleLabel = createLabel("Role: "+"Mafia",200,450);

        panel.add(playerName);
        panel.add(statusList);
        panel.add(playersList);
        panel.add(roleLabel);
        panel.add(mafiaLabel);
        panel.add(votingPortalLabel);
        panel.add(playersListLabel);
        mainFrame.setSize(900,700);
    }


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

    private void createMafiaList(int x_bound, int y_bound) {
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

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        return button;
    }

    @Override
    public void display(String[] playersName, GameStatus status) {
        JButton confirmButton = createButton(30, 500, "Confirm");
        panel.add(confirmButton);
        addListeners(confirmButton);

        createVoteList(-50, 100);
        panel.add(voteList);

        this.status = status;
        int x = 80, y = 70;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            customizeButton(x, y, player, button);
            y += 30;
            voteList.add(button);
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
    public void showMafia(String[] players) {
        createMafiaList(200, 100);
        for (String player : players) {
            defaultPlayersList.addElement(player);
        }
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

    private void sendMessage(JButton confirmButton) {
        if (status.equals(GameStatus.NIGHT)) controller.sendMessage(new MafiaVotedOutVillagerMessage(votedOutPlayer));
        else controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
        disableVoteButtons(confirmButton);
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
