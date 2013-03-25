package Controllers.Client;

import Channels.ConnectionListener;
import Channels.Messages.ChannelMessage;
import Channels.SocketChannel;
import Channels.SocketChannelListener;
import GameMessages.PlayerDetailsMessage;
import GameMessages.PlayersConnectedMessage;
import Views.JoinServerView;
import Views.PlayersConnectedView;

import java.io.IOException;

public class GameClientController implements SocketChannelListener,ConnectionListener {
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
        this.channel.bind(this);
        joinServerView.connectedToServer();
        this.channel.send(new PlayerDetailsMessage(joinServerView.getPlayerName()));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        joinServerView.connectionToServerFailed();
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        System.out.println("Unable to send Message");
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayersConnectedMessage) {
            PlayersConnectedMessage pCm = (PlayersConnectedMessage) message;
            playersConnectedView.displayConnectedPlayers(pCm);
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        System.out.println("Unable to read message");
    }

    public void register(PlayersConnectedView playersConnectedScreen) {

        this.playersConnectedView = playersConnectedScreen;
    }
}
