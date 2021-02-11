package start;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    public StartFrame() {
        setPreferredSize(new Dimension(600,600));
        pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x,y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
