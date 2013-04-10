package screens.server;

import controllers.server.GameEndController;
import views.server.GameEndView;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEndScreen implements GameEndView {
    private final IMainFrame mainFrame;
    private final GameEndController controller;
    private ImagePanel panel;
    private JLabel gameStatus;
    private JButton exit;
    private DefaultListModel<String> logModel = new DefaultListModel<String>();
    private JList<String> log = new JList<String>(logModel);


    public GameEndScreen(IMainFrame mainFrame, GameEndController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createImagePanel("images/gameEndScreen.jpg");
        exit = createButton(100, 300, "Exit");
        createList();
        panel.add(exit);
        panel.add(log);
        addListeners();
        mainFrame.setSize(400, 400);
    }

    private void addListeners() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToHomeScreen();
            }
        });
    }

    @Override
    public void display(String s) {
        gameStatus = new JLabel(s);
        gameStatus.setSize(400, 350);
        gameStatus.setLocation(80, 10);
        gameStatus.setFont(new Font("monospaced", Font.BOLD, 20));
        gameStatus.setForeground(Color.WHITE);
        panel.add(gameStatus);
        panel.repaint();
    }

    private void createList() {
        log.setSize(300, 450);
        log.setBorder(BorderFactory.createLineBorder(SystemColor.WHITE));
        log.setLocation(10, 100);
        log.setFont(new Font("Monospaced", Font.PLAIN, 14));
        log.setBackground(Color.orange);
    }

    @Override
    public void displayLog(String[] log) {
        for (String s : log) {
            logModel.addElement(s);
        }
    }


    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        return button;
    }
}
