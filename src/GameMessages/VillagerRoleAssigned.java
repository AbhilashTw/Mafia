package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class VillagerRoleAssigned extends ChannelMessage implements Serializable {
    private final String[] playersName;

    public VillagerRoleAssigned(String playersName) {
        this.playersName = playersName.split("\n");
    }

    public String[] getPlayersName() {
        return playersName;
    }
}
