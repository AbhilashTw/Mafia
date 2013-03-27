import channels.Server.SocketServer;
import controllers.Workflow;
import controllers.server.GameServerController;
import controllers.server.Player;
import gameMessages.PlayersConnectedMessage;
import org.junit.Test;
import views.server.GameServerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class GameServerControllerTest {
    private final String playerName = "MafiaPlayer";
    GameServerView gameServerView = mock(GameServerView.class);
    Workflow workflow = mock(Workflow.class);
    Player mockPlayer = mock(Player.class);
    SocketServer mockServer = mock(SocketServer.class);
    GameServerController gameServerController = new GameServerController(workflow);
    List<Player> players = new ArrayList<Player>();

    @Test
    public void when_gameServerController_calls_players_updated_it_invokes_updatePlayers_method_in_startServerScreen() throws IOException {
        gameServerController.bind(gameServerView);
        gameServerController.playersUpdated();
        verify(gameServerView).updatePlayers(players);
    }

    @Test
    public void players_connected_message_is_sent_whenever_a_new_player_is_updated() {
        when(mockPlayer.getName()).thenReturn(playerName);
        gameServerController.bind(gameServerView);
        gameServerController.addPlayer(mockPlayer);
        gameServerController.playersUpdated();
        verify(mockPlayer).sendMessage(PlayersConnectedMessage.createPlayersConnectedMessage(playerName + "\n"));
    }

    @Test
    public void stopServer_stops_the_server() {
        gameServerController.start(mockServer);
        gameServerController.stop();
        verify(mockServer).stop();
    }

    @Test
    public void stopServer_transitions_to_the_homeScreen() {

        gameServerController.stop();
        verify(workflow).start();
    }

    @Test
    public void startGame_invokes_Workflow_startGame() {
        gameServerController.startGame();
        verify(workflow).startGame(players);
    }

}
