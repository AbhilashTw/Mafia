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


    public HomeScreen(IMainFrame mainFrame, HomeController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        ImagePanel panel = mainFrame.createImagePanel(BG_IMAGE);

        startServerButton = createButton(100, 300, "StartServer");
        joinServerButton = createButton(100, 400, "JoinServer");
        quitButton = createButton(100, 500, "Quit");

        panel.add(startServerButton,addGridConstraints(100,100));
        panel.add(joinServerButton,addGridConstraints(100,200));
        panel.add(quitButton, addGridConstraints(100,300));

        addButtonHandlers();
    }

    private GridBagConstraints addGridConstraints( int xBound, int yBound) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = xBound;
        gbc.gridy = yBound;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor=GridBagConstraints.NORTHEAST;
        gbc.insets= new Insets(30,30,30,30);
        return gbc;
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
                int selectedOption = JOptionPane.showConfirmDialog(null, "Do you really want to quit?", "", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void display() {
    }

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
        return button;
    }

}
