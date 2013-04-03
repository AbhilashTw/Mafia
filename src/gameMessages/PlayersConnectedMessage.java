package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;
import java.util.Arrays;

public class PlayersConnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerName;

    private PlayersConnectedMessage(String playerNames) {
        super();
        this.playerName = playerNames.split("\n");
    }

    public static PlayersConnectedMessage createPlayersConnectedMessage(String playerNames) {
        if (playerNames == null) throw new IllegalArgumentException("PlayerName is null");
        return new PlayersConnectedMessage(playerNames);
    }

    public String[] getPlayersConnected() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayersConnectedMessage that = (PlayersConnectedMessage) o;

        return Arrays.equals(playerName, that.playerName);

    }

    @Override
    public int hashCode() {
        return playerName != null ? Arrays.hashCode(playerName) : 0;
    }
}
