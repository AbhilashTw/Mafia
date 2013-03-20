import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen {
    JFrame homeScreenFrame;
    JButton startServerButton;
    JButton joinServerButton;
    JButton quitButton;

    public HomeScreen() {
        homeScreenFrame = new JFrame("Mafia");
        homeScreenFrame.setLayout(null);
        homeScreenFrame.setBounds(100, 100, 600, 600);
        homeScreenFrame.setVisible(true);

        startServerButton = createButton(100, 300, "Start Server");
        joinServerButton = createButton(100, 400, "Join Server");
        quitButton = createButton(100, 500, "Quit");

        homeScreenFrame.add(startServerButton);
        homeScreenFrame.add(joinServerButton);
        homeScreenFrame.add(quitButton);
    }

    public void display() {

        joinServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JoinServer joinServerScreen = new JoinServer();
                joinServerScreen.enterServerName();
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

    private JButton createButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
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
                    hide();
                    System.exit(0); // TODO : its not closing the application.. close it cleanly...
                }
            }
        });
    }

    private void hide() {
        homeScreenFrame.setVisible(false);
    }
}