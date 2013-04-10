package gameMessages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class GameStatusLogMessage extends ChannelMessage implements Serializable {
    private String[] presentStatusLog;

    public GameStatusLogMessage() {
        super();
    }

    public void setLog(String[] presentStatusLog) {

        this.presentStatusLog = presentStatusLog;
    }

    public String[] getPresentStatusLog() {
        return presentStatusLog;
    }
}
