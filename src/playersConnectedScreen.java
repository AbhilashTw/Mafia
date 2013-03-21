import javax.swing.*;
import java.awt.*;

public class PlayersConnectedScreen {
    JFrame playersConnectedFrame;
    JPanel playersConnectedPanel;
    JLabel playersConnectedLabel;
    DefaultListModel<String> players = new DefaultListModel<String>();
    JList playersList = new JList(players);

    public PlayersConnectedScreen() {
        playersConnectedFrame = new JFrame("Players Joined");
        playersConnectedFrame.setBounds(100, 100, 600, 600);
        playersConnectedFrame.setVisible(true);

        playersConnectedPanel = new JPanel();
        playersConnectedPanel.setLayout(null);
        playersConnectedPanel.setBackground(Color.BLACK);

        playersConnectedLabel = new JLabel("Players Joined");
        Font font = new Font("Comic Sans MS", Font.PLAIN, 28);
        playersConnectedLabel.setFont(font);
        playersConnectedLabel.setForeground(Color.RED);
        playersConnectedLabel.setSize(200, 250);
        playersConnectedLabel.setLocation(50,-50);

        players.addElement("Melanie");
        players.addElement("Abhi");
        players.addElement("Mani");
        players.addElement("Sneha");

        playersList.setSize(200,150);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(50,100);
        Font f = new Font("Comic Sans MS", Font.PLAIN, 15);
        playersList.setFont(f);

        playersConnectedPanel.add(playersList);
        playersConnectedPanel.add(playersConnectedLabel);
        playersConnectedFrame.add(playersConnectedPanel);
    }
}
