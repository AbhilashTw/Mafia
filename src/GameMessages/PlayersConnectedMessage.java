package gameMessages;

import channels.Messages.ChannelMessage;

import java.io.Serializable;
import java.util.Arrays;

public class PlayersConnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerName;

    public PlayersConnectedMessage(String playerNames) {
        super();
        this.playerName = playerNames.split("\n");
    }

    public String[] getPlayersConnected() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayersConnectedMessage that = (PlayersConnectedMessage) o;

        if (!Arrays.equals(playerName, that.playerName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playerName != null ? Arrays.hashCode(playerName) : 0;
    }
}
