import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JoinServerScreen {
    String serverName;
    String playerName;

    public JoinServerScreen() {
        serverName = JOptionPane.showInputDialog("Enter the server name");
        playerName = JOptionPane.showInputDialog("Enter your name");
    }

    public void enterServerName() {
        connectToServer(serverName);

    }

    private void connectToServer(String serverName) {

    }
}


