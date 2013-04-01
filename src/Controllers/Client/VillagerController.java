package controllers.client;

import controllers.Workflow;
import views.client.VillagerView;

public class VillagerController implements ClientEngine {
    private final Workflow workflow;
    private final ClientPlayer clientPlayer;
    private VillagerView view;

    public VillagerController(Workflow workflow, ClientPlayer clientPlayer) {
        this.workflow = workflow;

        this.clientPlayer = clientPlayer;
        clientPlayer.bindClientEngine(this);
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
        //To change body of implemented methods use File | Settings | File Templates.
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
        view.updateStatus("Night has arrived");
    }

    @Override
    public void displayVillagerVotingChart(String[] playerNames) {
        view.updateStatus("Day has arrived");
        view.display(playerNames);
    }

}
