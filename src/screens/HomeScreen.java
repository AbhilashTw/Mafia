package screens;

import controllers.HomeController;
import screens.controls.IMainFrame;
import screens.controls.ImagePanel;
import views.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Job:- Understands to display the starting window of the application. */

public class HomeScreen implements HomeView {
    private static final String BG_IMAGE = "images/homepage.jpg";
    public final IMainFrame mainFrame;
    private final HomeController controller;
    private final JButton startServerButton;
    private final JButton joinServerButton;
    private final JButton quitButton;
    private JLabel messageLabel;


    public HomeScreen(IMainFrame mainFrame, HomeController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        ImagePanel panel = mainFrame.createImagePanel(BG_IMAGE);

        startServerButton = createButton(20, 25, "StartServer");
        joinServerButton = createButton(20, 100, "JoinServer");
        quitButton = createButton(20, 175, "Quit");
        messageLabel = createLabel(25, 250, "Welcome");
        mainFrame.setSize(900, 900);

        panel.add(startServerButton);
        panel.add(joinServerButton);
        panel.add(quitButton);
        panel.add(messageLabel);

        addButtonHandlers();
    }

    private JLabel createLabel(int xBound, int yBound, String message) {
        JLabel label = new JLabel(message);
        label.setSize(500, 50);
        label.setLocation(xBound, yBound);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Monospaced", Font.BOLD, 20));
        label.setVisible(true);
        return label;
    }


    private void addButtonHandlers() {
        startServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                controller.startServer();
            }
        });

        joinServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.joinServer();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });
    }

    @Override
    public void display() {
    }

    @Override
    public void displayMessage(String message) {
        messageLabel.setText(message);
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        return button;
    }

}
