package controllers;

import channels.Server.SocketServer;
import controllers.server.GameServerController;
import controllers.server.Player;
import gameMessages.PlayersConnectedMessage;
import org.junit.Test;
import controllers.server.Players;
import views.server.GameServerView;

import static org.mockito.Mockito.*;


public class GameServerControllerTest {
    GameServerView view = mock(GameServerView.class);
    Workflow workflow = mock(Workflow.class);
    Player player = mock(Player.class);
    SocketServer server = mock(SocketServer.class);
    Players gamePlayers = mock(Players.class);
    GameServerController controller = new GameServerController(workflow, gamePlayers);

    @Test
    public void when_gameServerController_calls_players_updated_it_invokes_updatePlayers_method_sends_message_to_the_player() {
        String testSample = "Abhilash" + "\n";
        when(gamePlayers.getPlayersName()).thenReturn(testSample);
        when(player.getName()).thenReturn(testSample);
        controller.bind(view);
        controller.playersUpdated();
        controller.addPlayer(player);
        verify(view).updatePlayers(gamePlayers.getPlayers());
        verify(gamePlayers).sendMessage(PlayersConnectedMessage.createPlayersConnectedMessage(testSample));
    }

    @Test
    public void start_invokes_start_from_Server() {
        controller.start(server);
        verify(server).start();
    }

    @Test
    public void startGame_invokes_Workflow_startGame() {
        controller.startGame();
        verify(workflow).startGame(gamePlayers);
    }
    @Test
    public void on_calling_addPlayer_it_invokes_Players_addPlayer(){
        controller.addPlayer(player);
        verify(gamePlayers).addPlayer(player);
    }
    @Test
    public void when_getPlayerListNames_is_called_it_invokes_getPlayerName_from_Players(){
        controller.getPlayersListName();
        verify(gamePlayers).getPlayersName();
    }

    @Test
    public void removePlayer_in_GameServerController_invokes_removePlayers_from_Players(){
        controller.removePlayer(player);
        verify(gamePlayers).removePlayer(player);
    }
    @Test
    public void canGameBeStarted_in_GameServerController_invokes_getPlayersCount_from_Players(){
        controller.canGameBeStarted();
        verify(gamePlayers).getPlayersCount();
    }
    @Test
    public void controllers_stop_invokes_Players_quit(){
        controller.start(server);
        controller.stop();
        verify(gamePlayers).quit();
    }
    @Test
    public void controllers_stop_invokes_servers_stop(){
        controller.start(server);
        controller.stop();
        verify(server).stop();
    }


}
