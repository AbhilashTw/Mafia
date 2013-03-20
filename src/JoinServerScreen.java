import javax.swing.*;
import java.io.IOException;


public class JoinServerScreen {

    JPanel displayMessage;
    String serverName;
    String playerName;

    public JoinServerScreen() {
        serverName = JOptionPane.showInputDialog("Enter the server name");
        playerName = JOptionPane.showInputDialog("Enter your name");
        StartServerScreen j = new StartServerScreen();
        j.displayPlayers(playerName);
    }

    public void enterServerName() {
        connectToServer(serverName);

    }

    private void connectToServer(String serverName) {
        try {
            Client client = Client.createClient(serverName, 1234);
            displayMessage = new JPanel();

            JOptionPane.showMessageDialog(displayMessage, "Connected to"+ serverName, "Connected", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
           displayMessage = new JPanel();
           JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to connect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


