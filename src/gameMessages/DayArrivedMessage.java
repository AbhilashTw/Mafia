package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;
import java.util.Arrays;

public class DayArrivedMessage extends ChannelMessage implements Serializable {
    private String[] playerNames;

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayersName(String[] playerNames) {

        this.playerNames = playerNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayArrivedMessage)) return false;

        DayArrivedMessage that = (DayArrivedMessage) o;

        if (!Arrays.equals(playerNames, that.playerNames)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playerNames != null ? Arrays.hashCode(playerNames) : 0;
    }
}
