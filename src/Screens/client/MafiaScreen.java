package screens.client;

import controllers.client.MafiaController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.client.MafiaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MafiaScreen implements MafiaView {

    private static final String BG_IMAGE = "images/MafiaStartScreen.jpg";
    private final JLabel timerLabel = new JLabel("10");
    private IMainFrame mainFrame;
    private MafiaController controller;
    private ImagePanel panel;
    private DefaultListModel<String> defaultStatusList = new DefaultListModel<String>();
    private ButtonGroup bg = new ButtonGroup();
    private JList statusList = new JList(defaultStatusList);
    private Timer timer;

    public MafiaScreen(IMainFrame mainFrame, MafiaController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel(BG_IMAGE);
        createList(400, 400);

        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBounds(800, 400, 200, 200);
        timerLabel.setSize(145, 50);

        panel.add(timerLabel);
        panel.add(statusList);
        changeStatus("Your assigned as a Mafia");
    }

    @Override
    public void display(String[] playersName) {
        changeStatus("Night Arrived");
        changeStatus("You Can Vote Now");
        timerScreen();
        int x = 100, y = 100;
        for (String player : playersName) {
            JRadioButton button = new JRadioButton(player);
            button.setLocation(x, y);
            button.setSize(145, 50);
            button.setBackground(Color.ORANGE);
            button.setVisible(true);
            bg.add(button);
            panel.add(button);
            y += 80;
        }
    }

    public void timerScreen() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timerLabel.getText());
                if (time > 0) timerLabel.setText(String.valueOf(time - 1));
                else if (time == 0) {
                    timer.stop();
                    sendVotes();
                }
            }
        });
        timer.start();
    }

    private void sendVotes() {
        String playerVotedOut = bg.getSelection().getActionCommand();
    }

    private void changeStatus(String status) {
        defaultStatusList.addElement(status);
    }

    private void createList(int x_bound, int y_bound) {
        statusList.setSize(600, 450);
        statusList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        statusList.setLocation(x_bound, y_bound);
        statusList.setBackground(Color.ORANGE);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        statusList.setFont(f);
    }

}

