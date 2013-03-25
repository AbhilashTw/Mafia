package Controllers.Server;

import Channels.Messages.ChannelMessage;
import Channels.ConnectionListener;
import Channels.Server.SocketServer;
import Channels.SocketChannel;
import GameMessages.PlayersConnectedMessage;
import Views.StartServerView;
import org.omg.CORBA.portable.ApplicationException;

import java.util.ArrayList;

public class GameServerController implements ConnectionListener, GameGod {

    SocketServer server = new SocketServer(1234, this);
    ArrayList<Player> players = new ArrayList<Player>();
    private StartServerView startServerView;

    public void start() {
        server.start();
    }

    public void bind(StartServerView startServerView) {
        this.startServerView = startServerView;
    }


    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        players.add(new Player(channel, this));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        throw new RuntimeException("Could not start server",e);
    }

    public void sendMessageToClients(ChannelMessage message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    public String getPlayersListName() {
        String resultName = "";
        for (Player player : players) {
            resultName += player.getName() + "\n";
        }
        return resultName;
    }

    @Override
    public void playersUpdated(Player player) {
        startServerView.updatePlayers(players);
        sendMessageToClients(new PlayersConnectedMessage(getPlayersListName()));
    }
}
