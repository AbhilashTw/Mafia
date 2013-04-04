package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class KilledPlayerMessage extends ChannelMessage implements Serializable {
    private String name;

    public KilledPlayerMessage() {
        super();
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }
}
