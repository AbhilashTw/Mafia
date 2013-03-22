package Runner;

import Screens.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        JFrame gameFrame = new JFrame("Mafia Game");
        gameFrame.setBounds(100, 100, 600, 600);
        gameFrame.setVisible(true);
        gameFrame.setBackground(Color.BLACK);
        HomeScreen newPlayer = new HomeScreen(gameFrame);
        newPlayer.display();
    }
}

