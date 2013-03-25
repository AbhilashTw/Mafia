package Screens;

import Controllers.Server.GameServerController;
import Screens.Controls.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Job:- Understands to display the start window of the application.
 */

public class HomeScreen {
    private static final String BG_IMAGE = "images/homepage.jpg";
    JFrame homeWindow;
    JButton startServerButton;
    JButton joinServerButton;
    JButton quitButton;
    ImagePanel homeScreenImage;

    public HomeScreen(JFrame gameFrame) {
        homeWindow = gameFrame;
        homeScreenImage = new ImagePanel(new ImageIcon(BG_IMAGE).getImage());
        homeWindow.getContentPane().add(homeScreenImage);
        homeWindow.pack();
        homeWindow.add(homeScreenImage);

        startServerButton = createButton(100, 300, "StartServer");
        joinServerButton = createButton(100, 400, "JoinServer");
        quitButton = createButton(100, 500, "Quit");

        homeScreenImage.add(startServerButton);
        homeScreenImage.add(joinServerButton);
        homeScreenImage.add(quitButton);
        homeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        startServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    startGameServer();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        joinServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JoinServerScreen joinServerScreenScreen = new JoinServerScreen(homeWindow);
                joinServerScreenScreen.connectToServer();
                homeScreenImage.setVisible(false);
            }
        });

        addHandlerForQuitButton();
    }

    private void startGameServer() throws IOException {
        GameServerController controller = new GameServerController();
        controller.start();
        StartServerScreen startServerScreen = new StartServerScreen(homeWindow, controller);
        homeScreenImage.setVisible(false);
        startServerScreen.display();
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        return button;
    }

    private void addHandlerForQuitButton() {
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(null, "Do you really want to quit?", "", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
