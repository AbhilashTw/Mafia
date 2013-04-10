package screens.controls;

import javax.swing.*;
import java.awt.*;

public interface IMainFrame {
    ImagePanel createImagePanel(String imageFilePath);

    JFrame getFrame();

    void setSize(int x, int y);

    Container getContainer();
}
