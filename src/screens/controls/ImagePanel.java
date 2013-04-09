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
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0E-4};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
