package Runner;

import Windows.HomeWindow;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        HomeWindow newPlayer = new HomeWindow();
        newPlayer.display();
    }
}

