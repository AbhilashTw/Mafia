package gameMessages;

import channels.Messages.ChannelMessage;

import java.io.Serializable;

public class PlayersConnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerName;

    public PlayersConnectedMessage(String playerNames) {
        super();
        this.playerName = playerNames.split("\n");
    }

    public String[] getPlayersConnected() {
        return playerName;
    }
}
