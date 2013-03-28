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
    private Role role;


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
        channel.stop();
        god.removePlayer(this);
        god.playersUpdated();
    }


    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        throw new RuntimeException("Send Failed",e);
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

    public void stop() {
        channel.stop();
    }

    public void setRole(Role mafia) {
        role = mafia;
    }

    public boolean isRoleAssigned() {
        return role != null;
    }

    public String getRole() {
        return role.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!channel.equals(player.channel)) return false;
        if (!name.equals(player.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = channel.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
