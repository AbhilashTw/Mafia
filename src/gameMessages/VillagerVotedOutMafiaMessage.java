package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class VillagerVotedOutMafiaMessage extends ChannelMessage implements Serializable {
    private final String votedOutPlayer;

    public VillagerVotedOutMafiaMessage(String votedOutPlayer) {
        super();
        this.votedOutPlayer = votedOutPlayer;
    }

    public String getVillagerVotedOutPlayer(){
        return    votedOutPlayer;
    }
}
