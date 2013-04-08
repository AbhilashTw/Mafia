package screens.controls;

import javax.swing.*;

public interface IMainFrame {
    ImagePanel createImagePanel(String imageFilePath);

    JFrame getFrame();
}
