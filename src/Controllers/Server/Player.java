package controllers.server;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;
import gameMessages.PlayerDetailsMessage;

import java.io.IOException;

public class Player implements SocketChannelListener {
    private final GameGod god;
    private SocketChannel channel;
    private String name;

    public Player(SocketChannel channel, GameGod god) {
        this.channel = channel;
        this.god = god;
        channel.bind(this);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(ChannelMessage message) {
        channel.send(message);
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        System.out.println("Unable to send in Server ");
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayerDetailsMessage) {
            PlayerDetailsMessage pdM = (PlayerDetailsMessage) message;
            name = pdM.getPlayerName();
            god.playersUpdated();
        }

    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }
}
