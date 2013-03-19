import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Job:- Understands to manage group of connections on a request to connect.

public class Server {
    private static int portNumber = 1234;
    ServerSocket server;
    Socket client;

    public Server() throws IOException {
        server = new ServerSocket(portNumber);
    }

    public void sendMessage() throws IOException {
        OutputStream s1out = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF("Connected");
        dos.close();
        closeClient();
    }

    public void closeClient() throws IOException {
        client.close();
    }

    private void listenToClient() throws IOException {
        client = server.accept();

    }

    public void close() throws IOException {
        server.close();
    }

    public void start() throws IOException {
        listenToClient();

    }

}

