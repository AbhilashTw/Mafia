package runner;

import channels.Server.SocketServer;
import channels.SocketChannel;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.JoinServerController;
import controllers.client.MafiaStartScreenController;
import controllers.client.PlayersListController;
import controllers.client.VillagerStartScreenController;
import controllers.server.GameServerController;
import controllers.server.NewConnectionListener;
import controllers.server.Players;
import controllers.server.PlayersRoleInfoController;
import screens.MafiaViewFactory;
import screens.client.JoinServerScreen;
import screens.client.MafiaStartScreen;
import screens.client.PlayersListScreen;
import screens.client.VillagerStartScreen;
import screens.controls.IMainFrame;
import screens.server.GameServerScreen;
import screens.server.PlayersRoleInfoScreen;

/**
 * Job : Understands procedural flow of data.
 */
public class WorkflowManager implements Workflow {

    private final MafiaViewFactory viewFactory;
    private IMainFrame mainFrame;
    private Players players;

    public WorkflowManager(MafiaViewFactory viewFactory, IMainFrame mainFrame, Players players) {
        this.viewFactory = viewFactory;
        this.mainFrame = mainFrame;
        this.players = players;
    }

    @Override
    public void startGame(Players players) {
        PlayersRoleInfoController controller = new PlayersRoleInfoController(players, this);
        controller.bind(new PlayersRoleInfoScreen(mainFrame, controller));
        controller.start();
    }


    @Override
    public void startServer() {
        GameServerController controller = new GameServerController(this, players);
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

    @Override
    public void start() {
        HomeController controller = viewFactory.getHomeController(this, mainFrame);
        controller.start();
    }

    @Override
    public void startVillagerScreen() {
        VillagerStartScreenController controller = new VillagerStartScreenController(this);
        controller.bind(new VillagerStartScreen(mainFrame, controller));
    }

    @Override
    public void startMafiaScreen() {
        MafiaStartScreenController controller = new MafiaStartScreenController(this, players);
        controller.bind(new MafiaStartScreen(mainFrame, controller));
    }

}
