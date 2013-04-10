package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class KnowVillagersMessage extends ChannelMessage implements Serializable {
    private String[] allPlayersName;

    public KnowVillagersMessage() {
        super();
    }

    public void setPlayers(String[] allPlayersName) {

        this.allPlayersName = allPlayersName;
    }

    public String[] getAllPlayersName() {
        return allPlayersName;
    }
}
