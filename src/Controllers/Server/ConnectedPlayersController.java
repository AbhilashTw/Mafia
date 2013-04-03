package controllers.server;

import controllers.Workflow;
import entities.Player;
import entities.Players;
import entities.Role;
import gameMessages.MafiaRoleAssigned;
import gameMessages.VillagerRoleAssigned;
import views.server.ConnectedPlayersView;

/**
 * Job: Understands to assign role to player.
 */
public class ConnectedPlayersController {
    private final Workflow workflow;
    private Players players;
    private ConnectedPlayersView view;


    public ConnectedPlayersController(Players players, Workflow workflowManager) {
        this.players = players;
        workflow = workflowManager;
    }

    public void bind(ConnectedPlayersView connectedPlayersView) {
        this.view = connectedPlayersView;
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
        workflow.showGameStatus();

    }

    private void sendClientsMessage() {
        for (Player player : players.getPlayers()) {
            if (player.getRole().equals(Role.Mafia.toString()))
                player.sendMessage(new MafiaRoleAssigned());
            else
                player.sendMessage(new VillagerRoleAssigned());
        }
    }

}
