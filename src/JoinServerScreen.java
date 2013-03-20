import javax.swing.*;
import java.awt.*;

public class JoinServerScreen {
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

    }
}


