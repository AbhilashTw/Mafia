package views.client;

import controllers.server.GameStatus;

public interface MafiaView {

    void display(String[] playersName, GameStatus night);

    void updateStatus(String status);

    void showMafia(String[] players);
}
