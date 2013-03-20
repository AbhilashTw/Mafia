import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen {
    JFrame homeScreenFrame;
    JPanel homeScreenPanel;
    JButton startServerButton;
    JButton joinServerButton;
    JButton quitButton;

    public HomeScreen() {
        homeScreenFrame = new JFrame("Mafia");
        homeScreenFrame.setBounds(100, 100, 600, 600);
        homeScreenFrame.setVisible(true);

        homeScreenPanel = new JPanel();
        homeScreenPanel.setLayout(new BoxLayout(homeScreenPanel, BoxLayout.Y_AXIS));
        startServerButton = createButton("Start Server");
        joinServerButton = createButton( "Join Server");
        quitButton = createButton( "Quit");

        homeScreenFrame.add(homeScreenPanel, BorderLayout.SOUTH);

        startServerButton = createButton("Start Server");
        joinServerButton = createButton("Join Server");
        quitButton = createButton("Quit");

        homeScreenPanel.add(startServerButton);
        homeScreenPanel.add(Box.createVerticalStrut(30));
        homeScreenPanel.add(joinServerButton);
        homeScreenPanel.add(Box.createVerticalStrut(30));
        homeScreenPanel.add(quitButton);
        homeScreenPanel.add(Box.createVerticalStrut(30));
    }

    public void display() {

        joinServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JoinServerScreen joinServerScreenScreen = new JoinServerScreen();
                joinServerScreenScreen.enterServerName();
            }
        });

        startServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StartServerScreen startServerScreen = new StartServerScreen();
                startServerScreen.display();
                hide();
            }
        });

        addHandlerForQuitButton();
    }

    private JButton createButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setFont(new Font("Verdana", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
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