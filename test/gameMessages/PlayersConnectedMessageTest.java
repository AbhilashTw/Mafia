package gameMessages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayersConnectedMessageTest {
    PlayersConnectedMessage playersConnectedMessage;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getPlayersConnected_returns_group_of_player_names() {
        String players = "Abhilash" + "\n" + "Sneha" + "\n";
        playersConnectedMessage = PlayersConnectedMessage.createPlayersConnectedMessage(players);
        String[] playersGroup = new String[]{"Abhilash", "Sneha"};
        Assert.assertEquals(playersGroup, playersConnectedMessage.getPlayersConnected());
    }

    @Test(expected = IllegalArgumentException.class)
    public void playersConnectedString_as_null_throw_IllegalArgumentException() {
        playersConnectedMessage = PlayersConnectedMessage.createPlayersConnectedMessage(null);

    }
}
