package controllers.client;

import channels.Messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.Player;
import controllers.server.Players;
import controllers.server.Role;
import views.client.MafiaStartScreenView;

public class MafiaStartScreenController {
    private Workflow workflow;
    private Players players;
    private MafiaStartScreenView view;

    public MafiaStartScreenController(Workflow workflow, Players players, ChannelMessage message) {
        this.workflow = workflow;
        this.players = players;
    }

    public void bind(MafiaStartScreenView view) {
        this.view = view;
    }

    public void start() {
        String villagersNames = getVillagersNames();
        int villagersCount = getVillagersCount();
        view.mafiaNightArrived(villagersNames, villagersCount);
    }

    private String getVillagersNames() {
        String villagersNames = "";
        for (Player player : players.getPlayers()) {
            if (player.getRole().equals(Role.Villager.toString())) {
                villagersNames += player.getName() + "\n";
            }
        }
        return villagersNames;
    }

    private int getVillagersCount() {
        int villagersCount = 2;
        for (Player player : players.getPlayers()) {
            if (player.getRole().equals(Role.Villager.toString())) {
                villagersCount++;
            }
        }
        return villagersCount;
    }
}
