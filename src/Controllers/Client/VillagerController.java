package controllers.client;

import controllers.Workflow;
import views.client.VillagerView;

public class VillagerController implements ClientEngine {
    private final Workflow workflow;
    private final ClientPlayerController clientPlayerController;
    private VillagerView view;

    public VillagerController(Workflow workflow, ClientPlayerController clientPlayerController) {
        this.workflow = workflow;
        this.clientPlayerController = clientPlayerController;
        clientPlayerController.bindClientEngine(this);
    }

    public void bind(VillagerView view) {
        this.view = view;
    }

    public void start() {
    }

    @Override
    public void displayConnectedPlayers(String[] playersConnected) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startVillagerScreen() {

    }

    @Override
    public void startMafiaScreen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void serverClosed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayMafiaVotingChart(String[] playerNames) {
        view.updateStatus("Night has arrived\n Good night");
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        view.display(playerNames);
    }

}
