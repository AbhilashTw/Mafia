package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;
import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NightArrivedMessage)) return false;

        NightArrivedMessage that = (NightArrivedMessage) o;

        if (!Arrays.equals(playersName, that.playersName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playersName != null ? Arrays.hashCode(playersName) : 0;
    }
}
