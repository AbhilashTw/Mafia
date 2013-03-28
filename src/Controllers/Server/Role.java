package controllers.server;

public enum Role {
    Villager,
    Mafia;

    public void assignRole(Player player) {
        player.setRole(this);
    }

}
