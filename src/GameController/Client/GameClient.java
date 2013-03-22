package GameController.Client;

import Channels.Messages.ChannelMessage;
import Channels.SocketChannel;
import Channels.SocketChannelListener;
import Screens.JoinServerScreen;
import Screens.PlayersConnectedScreen;

import java.io.IOException;

public class GameClient implements SocketChannelListener {
    private final int serverPort;
    private final JoinServerScreen joinServerScreen;
    private PlayersConnectedScreen playersConnectedScreen;

    public GameClient(int serverPort, JoinServerScreen joinServerScreen) {

        this.serverPort = serverPort;
        this.joinServerScreen = joinServerScreen;
    }

    public void connectToServer(String serverName) {
        SocketChannel.connectTo(serverName, serverPort, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        joinServerScreen.connectedToServer();
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        joinServerScreen.connectionToServerFailed();
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void register(PlayersConnectedScreen playersConnectedScreen) {

        this.playersConnectedScreen = playersConnectedScreen;
    }
}