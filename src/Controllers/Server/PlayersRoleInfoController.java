package controllers.server;

import controllers.Workflow;
import gameMessages.RoleAssignedMafia;
import gameMessages.RoleAssignedVillagers;
import views.server.PlayersRoleInfoView;

import java.util.List;
import java.util.Random;

public class PlayersRoleInfoController {
    private final List<Player> players;
    private final Workflow workflow;
    private PlayersRoleInfoView view;
    private int mafiaCount;
    private int villagerCount;

    public PlayersRoleInfoController(List<Player> players, Workflow workflowManager) {
        this.players = players;
        workflow = workflowManager;
    }

    public void bind(PlayersRoleInfoView playersRoleInfoView) {
        this.view = playersRoleInfoView;
    }

    public void assignRoles() {
        int assignedMafia = 0, assignedVillagers = 0;
        calculateRatio();
        for (Player player : players) {
            if (generateRandomNumber() == 0) {
                if (assignedMafia < mafiaCount) {
                    player.assignRole(Role.Mafia);
                    assignedMafia++;
                } else if (assignedVillagers < villagerCount) {
                    player.assignRole(Role.Villager);
                    assignedVillagers++;
                }
            } else {
                if (assignedVillagers < villagerCount) {
                    player.assignRole(Role.Villager);
                    assignedVillagers++;
                } else if (assignedMafia < mafiaCount) {
                    player.assignRole(Role.Mafia);
                    assignedMafia++;
                }
            }
        }
    }

    public void display() {
        view.display(players);

    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(2 - 0) + 0;
    }

    private void calculateRatio() {
        int totalCount = players.size();
        villagerCount = (totalCount / 2) + 1;
        mafiaCount = totalCount - villagerCount;
    }

    public void start() {
        assignRoles();
        sendClientsMessage();
        display();
    }

    private void sendClientsMessage() {
        for (Player player : players) {
            if (player.getRole().equals(Role.Mafia.toString()))
                player.sendMessage(new RoleAssignedMafia());
            else
                player.sendMessage(new RoleAssignedVillagers());
        }
    }

}
