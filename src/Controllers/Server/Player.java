package Controllers.Server;

import Channels.Messages.ChannelMessage;
import Channels.SocketChannel;
import Channels.SocketChannelListener;
import GameMessages.PlayerDetailsMessage;

import java.io.IOException;

public class Player implements SocketChannelListener {
    SocketChannel channel;
    private final GameGod god;
    private String name;

    public Player(SocketChannel channel, GameGod god) {
        this.channel = channel;
        this.god = god;

        channel.bind(this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
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
        if (message instanceof PlayerDetailsMessage) {
            PlayerDetailsMessage pdM = (PlayerDetailsMessage) message;
            name = pdM.getPlayerName();
            god.playersUpdated(this);
        }

    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getName() {
        return name;
    }
}
