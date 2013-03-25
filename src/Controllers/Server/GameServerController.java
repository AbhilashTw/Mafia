package controllers.server;

import channels.ConnectionListener;
import channels.Messages.ChannelMessage;
import channels.Server.SocketServer;
import channels.SocketChannel;
import gameMessages.PlayersConnectedMessage;
import controllers.Workflow;
import views.server.StartServerView;

import java.util.ArrayList;

public class GameServerController implements ConnectionListener, GameGod {

    private final Workflow workflow;
    SocketServer server = new SocketServer(1234, this);
    ArrayList<Player> players = new ArrayList<Player>();
    private StartServerView view;

    public GameServerController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void bind(StartServerView view) {
        this.view = view;
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
        view.updatePlayers(players);
        sendMessageToClients(new PlayersConnectedMessage(getPlayersListName()));
    }

    public void start() {
        server.start();
    }
}
