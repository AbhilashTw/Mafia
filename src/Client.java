//Job:- Understands to request a server for accepting its connection

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    Socket client;

    public Client() throws IOException {
        client = new Socket("localhost", 1234);
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