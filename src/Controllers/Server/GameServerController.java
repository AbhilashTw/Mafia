package Controllers.Server;

import Channels.Server.SocketServer;
import Channels.Server.SocketServerListener;
import Channels.SocketChannel;
import Views.StartServerView;

import java.util.ArrayList;

public class GameServerController implements SocketServerListener, GameGod {
    private StartServerView startServerView;
    SocketServer server = new SocketServer(1234,this);
    ArrayList<Player> players = new ArrayList<Player>() ;

    public void start() {
        server.start();
    }
    public void bind(StartServerView startServerView){

        this.startServerView = startServerView;
    }

    @Override
    public void onError(Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        players.add(new Player(channel,this));
    }

    @Override
    public void playersUpdated(Player player) {
        startServerView.updatePlayers(players);
    }
}
