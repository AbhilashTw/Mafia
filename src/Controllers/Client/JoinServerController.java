package controllers.client;

import channels.ConnectionListener;
import channels.SocketChannel;
import controllers.Workflow;
import views.client.JoinServerView;

/**
 * Job:- Understands to control connections between channels.
 */
public class JoinServerController implements ConnectionListener {
    private final Workflow workflow;
    private ClientPlayerController clientPlayerController;
    private JoinServerView view;
    private int serverPort = 1234;

    public JoinServerController(Workflow workflow, ClientPlayerController clientPlayerController) {
        this.workflow = workflow;
        this.clientPlayerController = clientPlayerController;
    }

    public void bind(JoinServerView view) {
        this.view = view;
    }

    public void start() {
    }

    public void connectToServer() {
        SocketChannel.connectTo(view.getServerName(), serverPort, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        clientPlayerController = new ClientPlayerController(channel, view.getServerName(), view.getPlayerName());
        workflow.connectedToServer(clientPlayerController);
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        view.connectionToServerFailed();
    }
}
