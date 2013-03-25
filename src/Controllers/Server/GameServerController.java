package Controllers.Server;

import Channels.Messages.ChannelMessage;
import Channels.Server.SocketServer;
import Channels.Server.SocketServerListener;
import Channels.SocketChannel;
import GameMessages.PlayersConnectedMessage;
import Views.StartServerView;

import java.util.ArrayList;

public class GameServerController implements SocketServerListener, GameGod {

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
    public void onError(Exception e) {
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        players.add(new Player(channel, this));
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
        sendMessageToClients(new PlayersConnectedMessage(getPlayersListName()));
        startServerView.updatePlayers(players);
    }
}
