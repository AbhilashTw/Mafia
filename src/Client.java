//Job:- Understands to request a server for accepting its connection

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    Socket client;
    private int portNumber;
    private String serverName;

    private Client(String serverName, int portNumber) throws IOException {
        this.serverName = serverName;
        this.portNumber = portNumber;
        client = new Socket(this.serverName, this.portNumber);
    }

    public static Client createClient(String serverName, int portNumber) throws IOException {
        return new Client(serverName, portNumber);
    }

    public String getServerMessage() throws IOException {
        InputStream clientInputStream = client.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(clientInputStream);
        String message = dataInputStream.readUTF();
        dataInputStream.close();
        clientInputStream.close();

        return message;

    }

    public void close() throws IOException {
        client.close();

    }
}