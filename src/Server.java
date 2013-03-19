import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Job:- Understands to manage group of connections on a request to connect.

public class Server {
    ServerSocket server;
    Socket client;

    public Server() throws IOException {
        server = new ServerSocket(1234);
    }

    private void sendMessage(Socket client) throws IOException {
        OutputStream s1out = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF("Connected");
        dos.close();
    }

    private void closeClient() throws IOException {
        client.close();
    }

    private void listenToClient() throws IOException {
        client = server.accept();

    }

    public void close() throws IOException {
        server.close();
    }

    public void runServer() throws IOException {
        listenToClient();
        sendMessage(client);
        closeClient();
    }

}

