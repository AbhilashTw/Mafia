import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen {
    JFrame homeScreen;
    JButton startServer;
    JButton joinServer;
    JButton quit;

    public HomeScreen() {
        homeScreen = new JFrame("Mafia");
        homeScreen.setLayout(null);
        homeScreen.setBounds(100, 100, 600, 600);
        homeScreen.setVisible(true);


        startServer = customizeButton(100, 300, "Start Server");
        joinServer = customizeButton(100, 400, "Join Server");
        quit = customizeButton(100, 500, "Quit");

        homeScreen.add(startServer);
        homeScreen.add(joinServer);
        homeScreen.add(quit);

    }

    public void display() {

        joinServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JoinServer joinServerScreen = new JoinServer();
                joinServerScreen.enterServerName();
            }
        });

        startServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close(HomeScreen.this.homeScreen);
                new StartServer();
            }
        });

        quitHomeScreen(quit);
    }

    private JButton customizeButton(int x_axis, int y_axis, String buttonName) {
        JButton button = new JButton(buttonName);
        button.setSize(145, 50);
        button.setLocation(x_axis, y_axis);
        Font font = new Font("Verdana", Font.BOLD, 14);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        return button;
    }

    private void quitHomeScreen(JButton quit) {
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(null, "Do you really want to quit?",
                        "", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    close(homeScreen);
                }
            }
        });
    }

    private void close(JFrame homeFrame) {
        homeFrame.setVisible(false);
    }

}