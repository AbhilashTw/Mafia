import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
   Job:- Understands to display the start window of the application
*/

public class HomeScreen {
    JFrame homeScreenFrame;
    JButton startServerButton;
    JButton joinServerButton;
    JButton quitButton;
    ImagePanel homeScreenImage;

    public HomeScreen() {
        homeScreenFrame = new JFrame("MAFIA");
        homeScreenFrame.setBounds(100, 100, 600, 600);
        homeScreenFrame.setVisible(true);
        homeScreenFrame.setBackground(Color.BLACK);

        homeScreenImage = new ImagePanel(new ImageIcon("images/homepage.jpg").getImage());

        homeScreenFrame.getContentPane().add(homeScreenImage);
        homeScreenFrame.pack();

        homeScreenFrame.add(homeScreenImage);

        startServerButton = createButton(100, 300, "Start Server");
        joinServerButton = createButton(100, 400, "Join Server");
        quitButton = createButton(100, 500, "Quit");

        startServerButton = createButton(100, 300, "Start Server");
        joinServerButton = createButton(100, 400, "Join Server");
        quitButton = createButton(100, 500, "Quit");

        homeScreenImage.add(startServerButton);
        homeScreenImage.add(joinServerButton);
        homeScreenImage.add(quitButton);
    }

    public void display() {

        startServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                StartServerScreen startServerScreen = new StartServerScreen();
                try {
                    startServerScreen.display();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                hide();
            }
        });

        joinServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JoinServerScreen joinServerScreenScreen = new JoinServerScreen();
                joinServerScreenScreen.connectToServer();
                hide();
            }
        });

        addHandlerForQuitButton();
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
                int selectedOption = JOptionPane.showConfirmDialog(null, "Do you really want to quit?",
                        "", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void hide() {
        homeScreenFrame.setVisible(false);
    }
}