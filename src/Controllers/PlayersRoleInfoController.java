package controllers;

import controllers.server.Player;
import runner.PlayersRoleInfoScreen;

import java.util.List;

public class PlayersRoleInfoController {
    private final List<Player> players;
    private PlayersRoleInfoScreen screen;

    public PlayersRoleInfoController(List<Player> players) {
        this.players = players;
    }

    public void bind(PlayersRoleInfoScreen playersRoleInfoScreen) {

        this.screen = playersRoleInfoScreen;
    }
}
