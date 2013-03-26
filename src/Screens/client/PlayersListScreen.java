package screens.client;

import controllers.client.PlayersListController;
import screens.controls.ImagePanel;
import screens.controls.MainFrame;
import views.client.PlayersConnectedView;

import javax.swing.*;
import java.awt.*;

public class PlayersListScreen implements PlayersConnectedView {
    private static final String BG_IMAGE = "images/joinServerScreen.jpg";
    private final PlayersListController controller;

    MainFrame mainFrame;
    private ImagePanel playersConnectedScreenImage;
    private JLabel playersConnectedLabel;
    private DefaultListModel<String> playersDefaultList = new DefaultListModel<String>();
    private JList playersList = new JList(playersDefaultList);


    public PlayersListScreen(MainFrame mainFrame, PlayersListController controller) {

        this.mainFrame = mainFrame;
        this.controller = controller;
        playersConnectedScreenImage = mainFrame.createImagePanel(BG_IMAGE);


        playersConnectedLabel = createLabel("Players Joined", 50, -50);
        createList(50, 100);

        playersConnectedScreenImage.add(playersList);
        playersConnectedScreenImage.add(playersConnectedLabel);
    }

    private void createList(int x_bound, int y_bound) {
        playersList.setSize(200, 850);
        playersList.setBorder(BorderFactory.createLineBorder(SystemColor.YELLOW));
        playersList.setLocation(x_bound, y_bound);
        playersList.setBackground(Color.YELLOW);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        playersList.setFont(f);
    }

    private JLabel createLabel(String labelName, int x_bound, int y_bound) {
        JLabel label = new JLabel(labelName);
        Font font = new Font("Comic Sans MS", Font.PLAIN, 28);
        label.setFont(font);
        label.setForeground(Color.RED);
        label.setSize(200, 250);
        label.setLocation(x_bound, y_bound);
        return label;
    }

    @Override
    public void displayConnectedPlayers(String[] players) {
        playersDefaultList.removeAllElements();
        for (String player : players) {
            playersDefaultList.addElement(player);
        }
    }

    @Override
    public void connectedToServer(String serverName, String playerName) {
        //Show those two fields on the screen
    }
}
