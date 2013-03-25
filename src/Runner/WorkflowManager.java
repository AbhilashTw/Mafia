package runner;

import channels.SocketChannel;
import controllers.client.JoinServerController;
import controllers.client.PlayersListController;
import controllers.HomeController;
import controllers.server.GameServerController;
import controllers.Workflow;
import screens.controls.MainFrame;
import screens.server.GameServerScreen;
import screens.HomeScreen;
import screens.client.JoinServerScreen;
import screens.client.PlayersListScreen;

public class WorkflowManager implements Workflow {

    private MainFrame mainFrame;

    public void start() {
        mainFrame = new MainFrame();
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startServer() {
        GameServerController controller = new GameServerController(this);
        controller.bind(new GameServerScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void joinServer() {
        JoinServerController controller = new JoinServerController(this);
        controller.bind(new JoinServerScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void connectedToServer(SocketChannel channel, String serverName, String playerName) {
        PlayersListController controller = new PlayersListController(this,channel,serverName,playerName);
        controller.bind(new PlayersListScreen(mainFrame,controller));
        controller.start();
    }
}
