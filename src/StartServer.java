import javax.swing.*;
import java.awt.*;

public class StartServer {
    JFrame startServer;
    JLabel playersJoined;
    JList playersList;
    JButton startGame;

    public StartServer() {
        startServer = new JFrame("Starting Server");
        startServer.setLayout(null);
        startServer.setBounds(100, 100, 600, 600);
        startServer.setVisible(true);

        playersJoined = new JLabel("Players Joined");
        Font font = new Font("Comic Sans MS", Font.PLAIN, 25);
        playersJoined.setFont(font);
        playersJoined.setForeground(Color.BLACK);
        playersJoined.setBackground(Color.WHITE);
        playersJoined.setSize(200, 250);
        playersJoined.setLocation(100, -50);

        startGame = new JButton("Start Game");
        startGame.setForeground(Color.WHITE);
        startGame.setBackground(Color.BLACK);
        startGame.setSize(100, 50);
        startGame.setLocation(400, 500);
        startServer.add(playersJoined);
        startServer.add(startGame);

        startServer.add(playersList);
    }

}
