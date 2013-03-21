import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

//Job:- Understands to display a window to connect to a server

public class StartServerScreen {
    public final int totalPlayers = 1;
    public Server server;
    JFrame startServerFrame;
    JLabel playersJoinedLabel;
    JButton startGameButton;
    DefaultListModel<String> players = new DefaultListModel<String>();
    JList<String> playersList;
    JPanel displayMessage;

    public StartServerScreen() {
        startServer();
        startServerFrame = new JFrame("Starting Server");
        startServerFrame.setBounds(100, 100, 600, 600);
        startServerFrame.setLayout(null);
        startServerFrame.getContentPane().setBackground(Color.ORANGE);

        //JLabel
        playersJoinedLabel = new JLabel("Players Joined");
        Font font = new Font("Monospaced", Font.BOLD, 25);
        playersJoinedLabel.setFont(font);
        playersJoinedLabel.setForeground(Color.WHITE);
        playersJoinedLabel.setBackground(Color.BLACK);
        playersJoinedLabel.setSize(100, 100);
        playersJoinedLabel.setLocation(100, -50);

        //StartButton
        startGameButton = new JButton("Start Game");
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBackground(Color.BLACK);
        startGameButton.setSize(100, 50);
        startGameButton.setLocation(400, 500);

        //JList
        playersList = new JList<String>(players);
        playersList.setSize(200, 200);
        playersList.setLocation(150, 100);
        Font f = new Font("Monospaced", Font.PLAIN, 15);
        playersList.setFont(f);

        //Frame Contents
        startServerFrame.add(playersList);
        startServerFrame.add(playersJoinedLabel);
        startServerFrame.add(startGameButton);
    }

    public void startServer() {
        try {
            server = Server.createServer(totalPlayers);
            server.startEvents();
        } catch (IOException e) {
            displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to Start Server.  Try Again", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void display() {
        startServerFrame.setVisible(true);
        players.addElement(server.getServerName());
        for (Socket client : server.getClients()) {
            players.addElement(client.getLocalAddress().getHostName());
        }
    }
}
