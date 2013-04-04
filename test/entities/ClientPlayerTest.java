package entities;

import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.client.ClientEngine;
import gameMessages.VillagerRoleAssigned;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientPlayerTest {
    ClientPlayer clientPlayer;
    SocketChannel channel;
    ChannelMessage message;
    ClientEngine engine;
    VillagerRoleAssigned villagermessage;


    @Before
    public void setUp() throws Exception {
        channel =  mock(SocketChannel.class);
        clientPlayer = new ClientPlayer(channel, "localHost", "Sneha");
        message = mock(ChannelMessage.class);
        engine = mock(ClientEngine.class);
        villagermessage = mock(VillagerRoleAssigned.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void stop_invokes_channels_stop() {
        clientPlayer.stop();
        verify(channel).stop();
    }

    @Test
    public void sendMessage_invokes_send_From_SocketChannel() {
        clientPlayer.sendMessage(message);
        verify(channel).send(message);
    }
    @Test
    public void onClose_invokes_ClientEngines_severClosed(){
        clientPlayer.bindClientEngine(engine);
        clientPlayer.onClose(channel, new Exception());
        verify(engine).serverClosed();
    }
    @Test
    public void onNewMessageArrives_invokes_ClientEngines_startVillagerScreen(){
        clientPlayer.bindClientEngine(engine);
        clientPlayer.onNewMessageArrived(channel, villagermessage);
        verify(engine).startVillagerScreen();
    }

}
