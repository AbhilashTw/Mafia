import javax.swing.*;
import java.io.IOException;

//Job:- Understands connecting a server to client based on the serverName
public class JoinServerScreen {
    Client client;
    JFrame JoinServerFrame;
    JPanel JoinServerPanel;
    JTextField serverName;
    JTextField playerName;
    JPanel displayMessage;

    public JoinServerScreen() {
        JoinServerFrame = new JFrame("Join Server");
        JoinServerFrame.setBounds(100, 100, 600, 600);
        JoinServerFrame.setVisible(true);

        JoinServerPanel = new JPanel();

        JoinServerFrame.add(JoinServerPanel);
        JoinServerPanel.add(serverName);
        JoinServerPanel.add(playerName);

        JoinServerPanel.setLayout(new BoxLayout(JoinServerPanel, BoxLayout.PAGE_AXIS));

        serverName = new JTextField("Enter Server Name");
        playerName = new JTextField("Enter your Name");

        StartServerScreen s = new StartServerScreen();
        s.displayPlayers(playerName.getText());
    }

    public void enterServerName() {
        connectToServer(serverName.getText());
    }

    private void connectToServer(String serverName) {
        try {
            client = Client.createClient(serverName, 1234);
            displayMessage = new JPanel();

            JOptionPane.showMessageDialog(displayMessage, "Connected to " + serverName, "Connected", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to connect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


