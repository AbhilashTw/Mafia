package ServerClient;//Job:- Understands to request a server for accepting its connection

import java.io.*;
import java.net.Socket;

public class Client {
    private final String clientName;
    private final Socket client;

    private Client(String serverName, int portNumber, String clientName) throws IOException {
        this.clientName = clientName;
        client = new Socket(serverName, portNumber);

    }

    public static Client createClient(String serverName, int portNumber, String clientName) throws IOException {
        if (serverName == null) throw new IllegalArgumentException("Improper Arguments serverNameLabel is null");
        return new Client(serverName, portNumber, clientName);
    }

    public String getServerMessage() throws IOException {
        InputStream clientInputStream = client.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(clientInputStream);
        String message = dataInputStream.readUTF();

        return message;
    }

    public void sendMessageToServer(String message) throws IOException {
        OutputStream s1out = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF(message);


    }

    public void close() throws IOException {
        client.close();
    }
}