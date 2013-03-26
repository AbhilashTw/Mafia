package controllers.server;

import channels.Server.SocketServer;
import channels.SocketChannel;
import channels.SocketChannelListener;
import controllers.Workflow;
import gameMessages.PlayersConnectedMessage;
import gameMessages.ServerDisconnectedMessage;
import screens.server.GameServerScreen;
import views.server.GameServerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class GameServerController implements GameGod {
    private final Workflow workflow;
    private SocketServer server;
    private List<Player> players = new ArrayList<Player>();
    private GameServerView view;

    public GameServerController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(GameServerView view) {
        this.view = view;
    }

    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }

    public String getPlayersListName() {
        String resultName = "";
        for (Player player : players) {
            resultName += player.getName() + "\n";
        }
        return resultName;
    }

    @Override
    public void playersUpdated() {
        view.updatePlayers(players);
        for (Player player : players) {
            player.sendMessage(PlayersConnectedMessage.createPlayersConnectedMessage(getPlayersListName()));
        }
    }

    public void start(SocketServer server) {
        this.server = server;
        server.start();
    }

    public void stop() {
        if (server != null)
            server.stop();

        workflow.start();
    }
}
