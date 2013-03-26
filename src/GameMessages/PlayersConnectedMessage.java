package gameMessages;

import channels.Messages.ChannelMessage;

import java.io.Serializable;

public class PlayersConnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerName;

    private PlayersConnectedMessage(String playerNames) {
        super();
        this.playerName = playerNames.split("\n");
    }

    public static PlayersConnectedMessage createPlayersConnectedMessage(String playerNames) {
        if (playerNames == null) throw new IllegalArgumentException("PlayerNames are null");
        return new PlayersConnectedMessage(playerNames);
    }

    public String[] getPlayersConnected() {
        return playerName;
    }
}
