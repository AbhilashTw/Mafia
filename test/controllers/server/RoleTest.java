package controllers.server;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoleTest {
    @Test
    public void when_assign_role_for_mafia_is_called_player_setRole_is_invoked() {
        Player player = mock(Player.class);
        player.setRole(Role.Mafia);
        verify(player).setRole(Role.Mafia);
    }

    @Test
    public void when_assign_role_for_villager_is_called_player_setRole_is_invoked() {
        Player player = mock(Player.class);
        player.setRole(Role.Villager);
        verify(player).setRole(Role.Villager);
    }
}
