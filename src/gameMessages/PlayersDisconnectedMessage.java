package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class PlayersDisconnectedMessage extends ChannelMessage implements Serializable {
    public PlayersDisconnectedMessage() {
        super();
    }
}
