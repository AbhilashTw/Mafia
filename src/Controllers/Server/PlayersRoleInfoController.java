package controllers.server;

import controllers.Workflow;
import gameMessages.RoleAssignedMafia;
import gameMessages.RoleAssignedVillagers;
import views.server.PlayersRoleInfoView;

public class PlayersRoleInfoController {
    private final Workflow workflow;
    private Players players;
    private PlayersRoleInfoView view;


    public PlayersRoleInfoController(Players players, Workflow workflowManager) {
        this.players = players;
        workflow = workflowManager;
    }

    public void bind(PlayersRoleInfoView playersRoleInfoView) {
        this.view = playersRoleInfoView;
    }

    public void assignRoles() {
        players.assignRoles();
    }

    public void display() {
        view.display(players.getPlayers());

    }

    public void start() {
        assignRoles();
        sendClientsMessage();
        display();
    }

    private void sendClientsMessage() {
        for (Player player : players.getPlayers()) {
            if (player.getRole().equals(Role.Mafia.toString()))
                player.sendMessage(new RoleAssignedMafia());
            else
                player.sendMessage(new RoleAssignedVillagers());
        }
    }

}
