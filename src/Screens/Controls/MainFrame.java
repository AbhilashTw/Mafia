package screens.controls;

import javax.swing.*;

/**
 * Job : Understands the window where game story plays.
 */
@SuppressWarnings("ALL")
public class MainFrame implements IMainFrame {
    private final JFrame frame;

    public MainFrame() {
        frame = new JFrame("Mafia Game");
        frame.setBounds(0, 0, 1280, 1024);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public ImagePanel createImagePanel(String imageFilePath) {
        frame.getContentPane().removeAll();
        frame.repaint();
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(imageFilePath).getImage());
        frame.getContentPane().add(imagePanel);
        return imagePanel;
    }

}
