package controllers.client;

import channels.ConnectionListener;
import channels.SocketChannel;
import controllers.Workflow;
import entities.ClientPlayer;
import views.client.JoinServerView;

/**
 * Job:- Understands to control connections between channels.
 */
public class JoinServerController implements ConnectionListener {
    private final Workflow workflow;
    private ClientPlayer clientPlayer;
    private JoinServerView view;

    public JoinServerController(Workflow workflow, ClientPlayer clientPlayer) {
        this.workflow = workflow;
        this.clientPlayer = clientPlayer;
    }

    public void bind(JoinServerView view) {
        this.view = view;
    }

    public void start() {
    }

    public void connectToServer() {
        int serverPort = 1234;
        SocketChannel.connectTo(view.getServerName(), serverPort, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        clientPlayer = new ClientPlayer(channel, view.getServerName(), view.getPlayerName());
        workflow.connectedToServer(clientPlayer);
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        view.connectionToServerFailed();
    }

    @Override
    public void connectionSuccessful() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
