package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class MafiaVotedOutVillagerMessage extends ChannelMessage implements Serializable {
    private final String votedOutPlayer;

    public MafiaVotedOutVillagerMessage(String votedOutPlayer) {
        this.votedOutPlayer = votedOutPlayer;
    }

    public String getMafiaVotedOutPlayer() {
        return votedOutPlayer;
    }
}
