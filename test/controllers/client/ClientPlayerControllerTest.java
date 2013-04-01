package controllers.client;

import channels.SocketChannel;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientPlayerControllerTest {
    @Test
    public void when_onClose_is_invoked_serverClosed_is_called() {
        ClientPlayerController controller = mock(ClientPlayerController.class);
        SocketChannel channel = mock(SocketChannel.class);
        ClientEngine clientEngine = mock(ClientEngine.class);
        controller.onClose(channel, new Exception());
        verify(clientEngine).serverClosed();
    }

}
