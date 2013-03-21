import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    public void sendMessage() throws IOException {
        for (Socket client : clients) {
            OutputStream s1out = client.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);
            dos.writeUTF("Connected");
            dos.close();
        }
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
        sendMessage();
    }

}



