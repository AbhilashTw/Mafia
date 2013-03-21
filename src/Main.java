import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        HomeScreen newPlayer = new HomeScreen();
        newPlayer.display();
        PlayersConnectedScreen newp = new PlayersConnectedScreen();
    }
}

