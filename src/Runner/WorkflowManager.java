package runner;


import channels.server.SocketServer;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.JoinServerController;
import controllers.client.JoinedPlayersController;
import controllers.client.MafiaController;
import controllers.client.VillagerController;
import controllers.server.*;
import entities.ClientPlayer;
import entities.Players;
import screens.MafiaViewFactory;
import screens.client.JoinServerScreen;
import screens.client.JoinedPlayersScreen;
import screens.client.MafiaScreen;
import screens.client.VillagerScreen;
import screens.controls.IMainFrame;
import screens.server.ConnectedPlayersScreen;
import screens.server.GameStatusScreen;
import screens.server.StartGameScreen;

/**
 * Job : Understands procedural flow of data.
 */
public class WorkflowManager implements Workflow {

    private final MafiaViewFactory viewFactory;
    private IMainFrame mainFrame;
    private Players players;
    private ClientPlayer clientPlayer;

    public WorkflowManager(MafiaViewFactory viewFactory, IMainFrame mainFrame) {
        this.viewFactory = viewFactory;
        this.mainFrame = mainFrame;
        this.players = new Players();
    }

    @Override
    public void start() {
        HomeController controller = viewFactory.getHomeController(this, mainFrame);
        controller.start();
    }

    @Override
    public void startServer() {

        StartGameController controller = new StartGameController(this, players);
        controller.bind(new StartGameScreen(mainFrame, controller));
        SocketServer server = new SocketServer(1234, new NewConnectionListener(controller));
        controller.start(server);
    }

    @Override
    public void joinServer() {
        JoinServerController controller = new JoinServerController(this, clientPlayer);
        controller.bind(new JoinServerScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void connectedToServer(ClientPlayer clientPlayer) {
        this.clientPlayer = clientPlayer;
        JoinedPlayersController controller = new JoinedPlayersController(this, clientPlayer);
        controller.bind(new JoinedPlayersScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startGame(Players players) {
        ConnectedPlayersController controller = new ConnectedPlayersController(players, this);
        controller.bind(new ConnectedPlayersScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startVillagerScreen() {
        VillagerController controller = new VillagerController(this, clientPlayer);
        controller.bind(new VillagerScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startMafiaScreen() {
        MafiaController controller = new MafiaController(this, clientPlayer);
        controller.bind(new MafiaScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void showGameStatus() {
        GameStatusController controller = new GameStatusController(this, players, new GamePlay());
        controller.bind(new GameStatusScreen(mainFrame, controller));
        controller.start();
    }

}
