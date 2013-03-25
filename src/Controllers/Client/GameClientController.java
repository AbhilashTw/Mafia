package Controllers.Client;

import Channels.Messages.ChannelMessage;
import Channels.SocketChannel;
import Channels.SocketChannelListener;
import GameMessages.PlayerDetailsMessage;
import Views.JoinServerView;
import Views.PlayersConnectedView;

import java.io.IOException;

public class GameClientController implements SocketChannelListener {
    private final int serverPort;
    private final JoinServerView joinServerView;
    private PlayersConnectedView playersConnectedView;
    private SocketChannel channel;

    public GameClientController(int serverPort, JoinServerView joinServerView) {

        this.serverPort = serverPort;
        this.joinServerView = joinServerView;
    }

    public void connectToServer(String serverName) {
        SocketChannel.connectTo(serverName, serverPort, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        this.channel = channel;
        joinServerView.connectedToServer();
        channel.send(new PlayerDetailsMessage(joinServerView.getPlayerName()));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        joinServerView.connectionToServerFailed();
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

    public void register(PlayersConnectedView playersConnectedScreen) {

        this.playersConnectedView = playersConnectedScreen;
    }
}
