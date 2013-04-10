package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class VillagerWinsMessage extends ChannelMessage implements Serializable {
    private String[] log;

    public VillagerWinsMessage() {
        super();
    }

    public void setLog(String[] log) {
        //To change body of created methods use File | Settings | File Templates.
        this.log = log;
    }

    public String[] getLog() {
        return log;
    }
}
