package ServerClient;

import java.net.Socket;

public class Player {
    Socket socket;
    String name;

    public Player(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
