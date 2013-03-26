package runner;

import channels.Server.SocketServer;
import channels.SocketChannel;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.JoinServerController;
import controllers.client.PlayersListController;
import controllers.server.GameServerController;
import controllers.server.NewConnectionListener;
import screens.HomeScreen;
import screens.MafiaViewFactory;
import screens.client.JoinServerScreen;
import screens.client.PlayersListScreen;
import screens.controls.IMainFrame;
import screens.controls.MainFrame;
import screens.server.GameServerScreen;

/**
 * Job : Understands procedural flow of data.
 */
public class WorkflowManager implements Workflow {

    private final MafiaViewFactory viewFactory;
    private IMainFrame mainFrame;

    public WorkflowManager(MafiaViewFactory viewFactory, IMainFrame mainFrame) {
        this.viewFactory = viewFactory;
        this.mainFrame = mainFrame;
    }

    @Override
    public void start() {
        HomeController controller = viewFactory.getHomeController(this, mainFrame);
        controller.start();
    }

    @Override
    public void startServer() {
        GameServerController controller = new GameServerController(this);
        controller.bind(new GameServerScreen(mainFrame, controller));

        SocketServer server = new SocketServer(1234, new NewConnectionListener(controller));
        controller.start(server);
    }

    @Override
    public void joinServer() {
        JoinServerController controller = new JoinServerController(this);
        controller.bind(new JoinServerScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void connectedToServer(SocketChannel channel, String serverName, String playerName) {
        PlayersListController controller = new PlayersListController(this, channel, serverName, playerName);
        controller.bind(new PlayersListScreen(mainFrame, controller));
        controller.start();
    }

}
