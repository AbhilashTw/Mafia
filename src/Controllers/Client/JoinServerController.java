package controllers.client;

import channels.ConnectionListener;
import channels.SocketChannel;
import controllers.Workflow;
import views.client.JoinServerView;

public class JoinServerController implements ConnectionListener {
    private final Workflow workflow;
    private JoinServerView view;
    private int serverPort = 1234;

    public JoinServerController(Workflow workflow) {

        this.workflow = workflow;
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
        workflow.connectedToServer(channel, view.getServerName(), view.getPlayerName());
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        view.connectionToServerFailed();
    }
}
