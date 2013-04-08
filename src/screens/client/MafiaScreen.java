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
import java.util.Enumeration;

public class MafiaScreen implements MafiaView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private IMainFrame mainFrame;
    private MafiaController controller;
    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private JList statusList = new JList<String>(defaultStatusList);
    private DefaultListModel<String> defaultMafiaList = new DefaultListModel<String>();
    private JList mafiaList = new JList<String>(defaultMafiaList);

    private JList voteList = new JList<JRadioButton>();
    private ButtonGroup bg = new ButtonGroup();
    private String votedOutPlayer;

    private GameStatus status;

    public MafiaScreen(IMainFrame mainFrame, MafiaController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);

        createList(700, 100);

        JLabel mafiaLabel = createLabel("You are assigned as a Mafia", 700, -50);
        JLabel votingPortalLabel = createLabel("Voting Portal", 150, -50);
        JLabel mafiaListLabel = createLabel("Mafians", 400, -50);

        panel.add(statusList);
        panel.add(mafiaList);
        panel.add(mafiaLabel);
        panel.add(votingPortalLabel);
        panel.add(mafiaListLabel);
    }


    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(200, 450);
        voteList.setLocation(xBound, yBound);
        voteList.setBackground(Color.BLACK);
        voteList.setForeground(Color.WHITE);
        voteList.setFont(new Font("Monospaced", Font.PLAIN, 20));
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(600, 450);
        statusList.setLocation(x_bound, y_bound);
        statusList.setBackground(Color.ORANGE);
        statusList.setForeground(Color.BLACK);
        statusList.setFont(new Font("Monospaced", Font.BOLD, 20));
    }

    private void createMafiaList(int x_bound, int y_bound) {
        mafiaList.setSize(200, 250);
        mafiaList.setLocation(x_bound, y_bound);
        mafiaList.setBackground(Color.ORANGE);
        mafiaList.setForeground(Color.BLACK);
        mafiaList.setFont(new Font("Monospaced", Font.PLAIN, 20));
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Monospaced", Font.BOLD, 16);
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
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        return button;
    }

    @Override
    public void display(String[] playersName, GameStatus status) {
        JButton confirmButton = createButton(50, 700, "Confirm");
        panel.add(confirmButton);
        createVoteList(100, 100);
        panel.add(voteList);
        addListeners(confirmButton);
        this.status = status;
        int x = 80, y = 80;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setVisible(true);
            button.setBackground(Color.ORANGE);
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
    public void showMafia(String[] players) {
        createMafiaList(400, 100);
        for (String player : players) {
            defaultMafiaList.addElement(player);
        }
        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void serverClosed() {

    }

    private void sendMessage(JButton confirmButton) {
        if (status.equals(GameStatus.NIGHT)) controller.sendMessage(new MafiaVotedOutVillagerMessage(votedOutPlayer));
        else controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
        disableVoteButtons(confirmButton);
    }

    private void disableVoteButtons(JButton confirmButton) {
        voteList.removeAll();
        confirmButton.setVisible(false);

        updateStatus("Your Voting Time Ended");
        Enumeration<AbstractButton> allButtons = bg.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }

}
