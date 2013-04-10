package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;


public class MafiaWinsMessage extends ChannelMessage implements Serializable {
    private String[] log;

    public MafiaWinsMessage() {
        super();
    }

    public void setLog(String[] log) {

        this.log = log;
    }

    public String[] getLog() {
        return log;
    }
}
