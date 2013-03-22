package GameController.Server;

import Channels.Messages.ChannelMessage;
import Channels.SocketChannel;
import Channels.SocketChannelListener;

import java.io.IOException;

public class GameServer implements SocketChannelListener {
    SocketChannel channel;


    public GameServer(SocketChannel channel) {

        this.channel = channel;
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
