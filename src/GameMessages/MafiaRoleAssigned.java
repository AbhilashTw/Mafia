package gameMessages;

import channels.Messages.ChannelMessage;

import java.io.Serializable;

public class MafiaRoleAssigned extends ChannelMessage implements Serializable {
    private final String[] playersName;

    public MafiaRoleAssigned(String playersName) {
        this.playersName = playersName.split("\n");
    }

    public String[] getPlayersName() {
        return playersName;
    }
}
