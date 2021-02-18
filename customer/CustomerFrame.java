package customer;

import javax.swing.*;
import java.awt.*;

public class CustomerFrame extends JFrame {
    public CustomerFrame() {
        setPreferredSize(new Dimension(1200,800));
        pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x,y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}