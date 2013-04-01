package gameMessages;

import org.junit.Assert;
import org.junit.Test;


public class NightArrivedMessageTest {
    @Test
    public void getPlayersName_gives_playersNames_on_setting_players_names() {
        String[] playersName = new String[]{"Abhilash"};
        NightArrivedMessage message = new NightArrivedMessage();
        message.setPlayersName(playersName);
        Assert.assertEquals(playersName, message.getPlayerNames());
    }

}
