package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class NightArrivedMessage extends ChannelMessage implements Serializable {
    private String[] playersName;

    public NightArrivedMessage() {
        super();
    }

    public void setPlayersName(String[] playersName) {
        this.playersName = playersName;
    }

    public String[] getPlayerNames() {
        return playersName;
    }
}
