package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class DayArrivedMessage extends ChannelMessage implements Serializable {
    private String[] playerNames;

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayersName(String[] playerNames) {

        this.playerNames = playerNames;
    }
}
