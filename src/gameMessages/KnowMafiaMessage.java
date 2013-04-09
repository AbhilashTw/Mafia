package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;
import java.util.Arrays;

public class KnowMafiaMessage extends ChannelMessage implements Serializable {
    private String[] players;

    public KnowMafiaMessage() {
        super();
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String[] getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowMafiaMessage)) return false;

        KnowMafiaMessage that = (KnowMafiaMessage) o;

        if (!Arrays.equals(players, that.players)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return players != null ? Arrays.hashCode(players) : 0;
    }
}
