import com.mocktpo.ui.windows.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice device = ge.getDefaultScreenDevice();
                MainFrame mainFrame = new MainFrame(device.getDefaultConfiguration());
                device.setFullScreenWindow(mainFrame);
                mainFrame.setVisible(true);
            }
        });
    }
}
