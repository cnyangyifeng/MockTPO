import com.mocktpo.ui.MainFrame;

import java.awt.*;

public class Application {

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = ge.getDefaultScreenDevice();
        MainFrame main = new MainFrame(device.getDefaultConfiguration());
        device.setFullScreenWindow(main);
        main.setVisible(true);
    }

}
