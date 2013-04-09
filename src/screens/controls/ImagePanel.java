package screens.controls;

import javax.swing.*;
import java.awt.*;

/* *
 * Job : Understands to display the picture as background of panel.
 */

public class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);
        setMinimumSize(screenSize);
        setMaximumSize(screenSize);
        setSize(screenSize);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
