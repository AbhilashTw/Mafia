package gameMessages;

import channels.Messages.ChannelMessage;

import java.io.Serializable;

public class PlayerDetailsMessage extends ChannelMessage implements Serializable {
    private final String playerName;

    private PlayerDetailsMessage(String playerName) {
        super();
        this.playerName = playerName;
    }

    public static PlayerDetailsMessage createPlayerDetailsMessage(String playerName) {
        if (playerName == null) throw new IllegalArgumentException("PlayerDetail is Null");
        return new PlayerDetailsMessage(playerName);
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDetailsMessage)) return false;
        PlayerDetailsMessage that = (PlayerDetailsMessage) o;
        if (playerName != null ? !playerName.equals(that.playerName) : that.playerName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return playerName != null ? playerName.hashCode() : 0;
    }
}
