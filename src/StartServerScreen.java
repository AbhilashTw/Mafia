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
    JList playersList;
    DefaultListModel model;



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


        startServerFrame.add(playersJoinedLabel);
        startServerFrame.add(startGameButton);
    }

    public void display() {
        startServerFrame.setVisible(true);
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
        JScrollPane pane = new JScrollPane(playersList);
        model = new DefaultListModel();
        playersList = new JList(model);
        pane.setVisible(true);
        model.addElement(playerName);
        startServerFrame.add(pane, BorderLayout.NORTH);
    }
}
