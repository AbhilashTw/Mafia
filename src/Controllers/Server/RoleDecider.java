package controllers.server;

import java.util.Random;

public class RoleDecider {
    private final Players players;
    private int mafiaCount;
    private int villagerCount;

    public RoleDecider(Players players) {
        this.players = players;
    }

    public void assignRoles() {
        calculateRatio();
        for (Player player : players.getPlayers()) {
            if (getRandomDecision()) {
                assignAsMafia(player);
            } else {
                assignAsVillager(player);
            }
        }
    }

    private void assignAsVillager(Player player) {
        if (villagerCount > 0) {
            Role.Villager.assignRole(player);
            villagerCount--;
        } else if (mafiaCount > 0) {
            Role.Mafia.assignRole(player);
            mafiaCount--;
        }
    }

    private void assignAsMafia(Player player) {
        if (mafiaCount > 0) {
            Role.Mafia.assignRole(player);
            mafiaCount--;
        } else if (villagerCount > 0) {
            Role.Villager.assignRole(player);
            villagerCount--;
        }
    }

    private boolean getRandomDecision() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private void calculateRatio() {
        int totalCount = players.getPlayersCount();
        villagerCount = (totalCount / 2) + 1;
        mafiaCount = totalCount - villagerCount;
    }
}
