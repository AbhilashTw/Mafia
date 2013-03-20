import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Job:- Understands to display a window to connect to a server

public class StartServerScreen {
    JFrame startServerFrame;
    JLabel playersJoinedLabel;
    JButton startGameButton;
    DefaultListModel<String> players = new DefaultListModel<String>();
    JList playersList = new JList(players);


    public StartServerScreen() {
        startServerFrame = new JFrame("Starting Server");
        startServerFrame.setBounds(100, 100, 600, 600);
        startServerFrame.setLayout(null);

        playersJoinedLabel = new JLabel("Players Joined");
        Font font = new Font("Comic Sans MS", Font.PLAIN, 25);
        playersJoinedLabel.setFont(font);
        playersJoinedLabel.setForeground(Color.BLACK);
        playersJoinedLabel.setBackground(Color.WHITE);
        playersJoinedLabel.setSize(200, 250);
        playersJoinedLabel.setLocation(100, -50);

        startGameButton = new JButton("Start Game");
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBackground(Color.BLACK);
        startGameButton.setSize(100, 50);
        startGameButton.setLocation(400, 500);


        players.addElement("Melanie");
        players.addElement("Abhi");
        players.addElement("Mani");
        players.addElement("Sneha");

        startServerFrame.add(playersList);
        playersList.setSize(100, 100);
        playersList.setLocation(100, 100);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 15);
        playersList.setFont(f);


        startServerFrame.add(playersJoinedLabel);
        startServerFrame.add(startGameButton);
    }

    public void display() {
        startServerFrame.setVisible(true);
        String subject[] = {"Math", "Computer", "Phisics", "Chemestry"};
        playersList = new JList<String>(subject); //
        JPanel panel = new JPanel();
        panel.add(playersList);
        startServerFrame.add(panel);
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Server server = Server.createServer(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void displayPlayers(String playerName) {


    }
}
