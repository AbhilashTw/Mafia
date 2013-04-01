package controllers.client;

import channels.messages.ChannelMessage;

public interface ClientEngine {
    void displayConnectedPlayers(String[] playersConnected);

    void startVillagerScreen(ChannelMessage message);

    void startMafiaScreen(ChannelMessage message);

    void serverClosed();
}
