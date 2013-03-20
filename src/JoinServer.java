import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinServer {
    String serverName;
    String playerName;

    public JoinServer() {
        serverName = JOptionPane.showInputDialog("Enter the server name");
        playerName = JOptionPane.showInputDialog("Enter your name");
    }

    public void enterServerName() {
        connectToServer(serverName);
    }

    private void connectToServer(String serverName) {

    }
}


