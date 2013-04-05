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
    private JLabel timerLabel = new JLabel("30");
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

        createList(900, 100);
        createTimerLabel();

        panel.add(timerLabel);
        panel.add(statusList);
        panel.add(mafiaList);

        updateStatus("Your assigned as a Mafia");
    }

    private void createTimerLabel() {
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setLocation(900, 800);
        timerLabel.setSize(200, 200);
        Font timerFont = new Font("Monospaced", Font.PLAIN, 100);
        timerLabel.setFont(timerFont);
    }

    private void createVoteList(int xBound, int yBound) {
        voteList.setSize(200, 650);
        voteList.setLocation(xBound, yBound);
        voteList.setOpaque(false);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        voteList.setFont(f);
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(600, 450);
        statusList.setLocation(x_bound, y_bound);
        statusList.setOpaque(false);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        statusList.setFont(f);
    }


    private void createMafiaList(int x_bound, int y_bound) {
        mafiaList.setSize(600, 450);
        mafiaList.setLocation(x_bound, y_bound);
        mafiaList.setOpaque(false);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        mafiaList.setFont(f);

    }


    @Override
    public void display(String[] playersName, GameStatus status) {
        createVoteList(100, 100);
        panel.add(voteList);
        this.status = status;
        timerScreen();
        int x = 100, y = 100;
        for (String player : playersName) {
            AbstractButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setVisible(true);
            bg.add(button);
            voteList.add(button);
            if( controller.getClientName().equals(player) ) {
                button.setSelected(true);
                votedOutPlayer=controller.getClientName();
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
    }

    @Override
    public void showMafia(String[] players) {
        defaultMafiaList.addElement("Mafia Players");
        createMafiaList(400, 600);
        for (String player : players) {
            defaultMafiaList.addElement(player);
        }
    }

    public void timerScreen() {
        timerLabel.setText("30");
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timerLabel.getText());
                if (time > 0) timerLabel.setText(String.valueOf(time - 1));
                else if (time == 0) {
                    sendMessage();
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void sendMessage() {
        if (status.equals(GameStatus.NIGHT)) controller.sendMessage(new MafiaVotedOutVillagerMessage(votedOutPlayer));
        else controller.sendMessage(new VillagerVotedOutMafiaMessage(votedOutPlayer));
        disableVoteButtons();
    }

    private void disableVoteButtons() {
        voteList.removeAll();
        updateStatus("Your Voting Time Ended");
        Enumeration<AbstractButton> allButtons = bg.getElements();
        while (allButtons.hasMoreElements()) {
            allButtons.nextElement().setVisible(false);
        }
    }
}
