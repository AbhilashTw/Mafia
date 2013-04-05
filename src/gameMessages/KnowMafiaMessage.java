package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class KnowMafiaMessage extends ChannelMessage implements Serializable {
    private String[] players;

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String[] getPlayers() {
        return players;
    }
}
