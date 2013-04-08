package screens.controls;

import javax.swing.*;
import java.awt.*;

/**
 * Job : Understands the window where game story plays.
 */
@SuppressWarnings("ALL")
public class MainFrame implements IMainFrame {
    private final JFrame frame;

    public MainFrame() {
        frame = new JFrame("Mafia Game");
        frame.setBounds(100, 110, 1000, 1000);
        frame.setVisible(true);

        frame.setBackground(Color.black);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(1000, 1000));
    }

    @Override
    public ImagePanel createImagePanel(String imageFilePath) {
        frame.getContentPane().removeAll();
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(imageFilePath).getImage());
        frame.getContentPane().add(imagePanel);
        frame.repaint();
        frame.pack();
        return imagePanel;
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

}
