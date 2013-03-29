package controllers.client;

import channels.Messages.ChannelMessage;

public interface ClientEngine {
    void displayConnectedPlayers(String[] playersConnected);

    void startVillagerScreen(ChannelMessage message);

    void startMafiaScreen(ChannelMessage message);

    void ServerClosed();
}
