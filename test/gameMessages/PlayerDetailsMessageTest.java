package gameMessages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerDetailsMessageTest {
    PlayerDetailsMessage playerDetailsMessage;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getPlayerName_gives_PlayerName() {
        String playerName = "Abhilash";
        playerDetailsMessage = PlayerDetailsMessage.createPlayerDetailsMessage(playerName);
        Assert.assertEquals(playerName, playerDetailsMessage.getPlayerName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void playerName_as_null_throws_IllegalArgumentException() {
        PlayerDetailsMessage.createPlayerDetailsMessage(null);
    }

}
