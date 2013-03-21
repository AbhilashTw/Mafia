import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
    Job:- Understands to run one or more services.
*/
public class Server {
    private static int portNumber = 1234;
    public final List<Socket> clients;
    private final int backlog;
    private final ServerSocket server;
    public List<Player> players;

    private Server(int backlog) throws IOException {
        this.backlog = backlog;
        server = new ServerSocket(portNumber);
        clients = new ArrayList<Socket>();
    }

    public static Server createServer(int backlog) throws IOException {
        if (backlog <= 0) throw new IllegalArgumentException("Invalid number of clients" + backlog);
        return new Server(backlog);
    }

    private void listenToClient() throws IOException {
        int count = 0;
        while (count < backlog) {
            Socket client = server.accept();
            clients.add(client);
            count++;
        }
    }

    public void sendMessage(String message) throws IOException {
        for (Socket client : clients) {
            OutputStream s1out = client.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);
            dos.writeUTF(message);
            // dos.close();
        }
    }

    public String getClientMessage(Socket client) throws IOException {
        InputStream clientInputStream = client.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(clientInputStream);
        String message = dataInputStream.readUTF();
//      dataInputStream.close();
//      clientInputStream.close();
        return message;
    }

    public List<Socket> getClients() {
        return clients;
    }

    public void close() throws IOException {
        server.close();
    }

    public void closeClient() throws IOException {
        for (Socket client : clients) {
            client.close();
        }
    }

    public String getServerName() {
        return server.getLocalSocketAddress().toString();
    }

    public void startEvents() throws IOException {
        listenToClient();
        sendMessage("Connected");
        createPlayersList();

    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getClientsListToString() {
        String result = null;
        for (Player player : players) {
            System.out.println(player.getName());
            result += player.getName() + "\n";

        }
        return result;
    }

    public void createPlayersList() throws IOException {
        String message;
        for (Socket client : clients) {
            message = getClientMessage(client);
            players.add(new Player(client, message));
        }
    }

}



