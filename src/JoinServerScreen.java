import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Job:- Understands connecting a server to client based on the serverName
public class JoinServerScreen {
    JFrame joinServerFrame;
    JPanel joinServerPanel;
    JLabel serverName;
    JLabel name;
    JTextField serverNameText;
    JTextField nameText;
    JButton connectButton;
    Client client;
    JPanel displayMessage;

    public JoinServerScreen() {

        joinServerFrame = new JFrame("JoinServer");
        joinServerPanel = new JPanel();

        joinServerPanel.setLayout(null);

        serverName = new JLabel("Server Name");
        serverName.setForeground(Color.white);
        serverName.setSize(100, 100);
        serverName.setLocation(100, 100);

        serverNameText = new JTextField();
        serverNameText.setSize(100, 30);
        serverNameText.setLocation(200, 200);

        name = new JLabel("Player Name");
        name.setForeground(Color.white);
        name.setSize(100, 100);
        name.setLocation(100, 200);

        nameText = new JTextField();
        nameText.setSize(100, 30);
        nameText.setLocation(200, 300);

        connectButton = new JButton("Connect");
        connectButton.setBackground(Color.black);
        connectButton.setForeground(Color.white);
        connectButton.setSize(100, 50);
        connectButton.setLocation(350, 450);

        joinServerPanel.add(serverName);
        joinServerPanel.add(name);
        joinServerPanel.add(serverNameText);
        joinServerPanel.add(nameText);
        joinServerPanel.add(connectButton);


        joinServerPanel.setBackground(Color.orange);
        joinServerFrame.add(joinServerPanel);
        joinServerFrame.setVisible(true);
        joinServerFrame.getContentPane().setBackground(Color.ORANGE);

        joinServerFrame.setBounds(100, 100, 600, 600);

    }

    public void connectToServer() {
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client = Client.createClient(serverNameText.getText(), 1234, nameText.getText());
                    if (client.getServerMessage().equals("Connected")) {
                        client.sendMessageToServer(nameText.getText());
                        PlayersConnectedScreen screen = new PlayersConnectedScreen(client);
                        screen.display();
                        joinServerFrame.setVisible(false);
                    }
                } catch (IOException f) {
                    displayMessage = new JPanel();
                    JOptionPane.showMessageDialog(displayMessage, "Sorry , Unable to connect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}


