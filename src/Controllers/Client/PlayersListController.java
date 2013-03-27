package controllers.client;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;
import controllers.Workflow;
import gameMessages.PlayerDetailsMessage;
import gameMessages.PlayersConnectedMessage;
import views.client.PlayersConnectedView;

import java.io.IOException;

/**
 * Job:-
 */
public class PlayersListController implements SocketChannelListener {

    private final Workflow workflow;
    private final String serverName;
    private final String playerName;
    private SocketChannel channel;
    private PlayersConnectedView view;

    public PlayersListController(Workflow workflow, SocketChannel channel, String serverName, String playerName) {

        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
        this.playerName = playerName;
        this.channel.bind(this);
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
        throw new RuntimeException("close failed", e);
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
        throw new RuntimeException("send failed", e);
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayersConnectedMessage) {
            PlayersConnectedMessage pCm = (PlayersConnectedMessage) message;
            view.displayConnectedPlayers(pCm.getPlayersConnected());
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
        throw new RuntimeException("message read error", e);
    }

    public void bind(PlayersConnectedView view) {

        this.view = view;
    }

    public void start() {
        view.connectedToServer(serverName, playerName);
        this.channel.send(PlayerDetailsMessage.createPlayerDetailsMessage(playerName));
    }
}
