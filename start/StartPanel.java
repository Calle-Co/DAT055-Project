package start;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    public StartPanel() {
        setSize(600, 600);
        setBackground(Color.GREEN);
        setLayout(new GridLayout(1,3));
        add(new JButton("1"));
        add(new JButton("2"));
        add(new JButton("3"));
        setVisible(true);
    }
}
