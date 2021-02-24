package customer.home;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.tools.Tool;

public class KEVINTEST {
    public static void main(String[] args) {
        JFrame s = new JFrame();
        s.setPreferredSize(new Dimension(1200, 800));
        s.setResizable(false);
        s.add(new HomeView());
        s.pack();
        s.setVisible(true);
        s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
