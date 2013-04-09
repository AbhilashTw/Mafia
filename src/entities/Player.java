package entities;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.server.GameEngine;
import gameMessages.MafiaVotedOutVillagerMessage;
import gameMessages.PlayerDetailsMessage;
import gameMessages.VillagerVotedOutMafiaMessage;

import java.io.IOException;

/*
  Job: Understands the one who participate in the game.
*/
public class Player implements SocketChannelListener {

    private GameEngine god;
    private SocketChannel channel;
    private String name;
    private Role role;


    public Player(SocketChannel channel, GameEngine god) {
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
        god.removePlayer(this, controllers.server.GameStatus.NIGHT);
        god.playersUpdated();
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        System.out.println(name + role + "Sending Failed " + channel.getAddress());
        channel.send(message);
        e.printStackTrace();
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayerDetailsMessage) {
            PlayerDetailsMessage pdM = (PlayerDetailsMessage) message;
            name = pdM.getPlayerName();
            god.playersUpdated();
        }
        if (message instanceof MafiaVotedOutVillagerMessage) {
            god.updateMafiaVotes(getName(), ((MafiaVotedOutVillagerMessage) message).getMafiaVotedOutPlayer());
        }

        if (message instanceof VillagerVotedOutMafiaMessage) {
            god.updateVillagerVotes(getName(), ((VillagerVotedOutMafiaMessage) message).getVillagerVotedOutPlayer());
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        System.out.println(name + role + "Read Failed " + channel.getAddress());
        e.printStackTrace();
    }

    public void stop() {
        channel.stop();
    }

    public boolean isRoleAssigned() {
        return role != null;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(Role mafia) {
        role = mafia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return channel.equals(player.channel) && name.equals(player.name);

    }

    @Override
    public int hashCode() {
        int result = channel.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.god = gameEngine;
    }

    public boolean isMafia() {
        return role == Role.Mafia;
    }
}
