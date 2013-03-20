import javax.swing.*;
import java.io.IOException;

//Job:- Understands connecting a server to client based on the serverName
public class JoinServerScreen {
    Client client;
    JPanel displayMessage;
    String serverName;
    String playerName;

    public JoinServerScreen() {
        serverName = JOptionPane.showInputDialog("Enter the server name");
        playerName = JOptionPane.showInputDialog("Enter your name");
        StartServerScreen j = new StartServerScreen();

    }

    public void enterServerName() {
        connectToServer(serverName);

    }

    private void connectToServer(String serverName) {
        try {
            client = Client.createClient(serverName, 1234);
            displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Connected to" + serverName, "Connected", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            displayMessage = new JPanel();
            JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to connect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


