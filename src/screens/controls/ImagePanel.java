package screens.controls;

import javax.swing.*;
import java.awt.*;

/**
 * Job : Understands to display the picture as background of panel.
 */
public class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        //  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); todo: Screen Size disabled
        Dimension size = new Dimension(1000, 1000);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setForeground(Color.BLACK);
        setBackground(Color.BLACK);
        setLayout(null);
    }

//    public void paintComponent(Graphics g) {
    //        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);   //todo: Image is disabled
//    }
}
