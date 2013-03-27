package controllers.server;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;
import controllers.Workflow;
import gameMessages.PlayerDetailsMessage;
import gameMessages.RoleAssignedMessage;

import java.io.IOException;

public class Player implements SocketChannelListener {
    private final GameGod god;
    private SocketChannel channel;
    private String name;
    private Workflow workflow;
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

    @Override
    public void onClose(SocketChannel channel, Exception e) {
        channel.stop();
        god.removePlayer(this);
        god.playersUpdated();
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
        if (message instanceof RoleAssignedMessage) {
            if (message.equals("villager")) { //todo : string comparison
                workflow.startVillagerScreen();
            } else if (message.equals("mafia"))
                workflow.startMafiaScreen();
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }

    public void stop() {
        channel.stop();
    }


    public void assignRole(Role mafia) {
        role = mafia;
    }

    public boolean isRoleAssigned() {
        return role != null;
    }

    public String getRole() {
        return role.toString();
    }
}
