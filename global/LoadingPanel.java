package global;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JFrame {
    LoadingPanel() {
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        JLabel label = new JLabel("Loading...");
        topPanel.add(label);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        //put gif as icon in an imageIcon
        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL imageURL = cldr.getResource("global/Resources/loading.gif");
        ImageIcon imageIcon = new ImageIcon(imageURL);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(imageIcon);
        centerPanel.add(iconLabel);
        
        JPanel all = new JPanel();
        all.setBackground(Color.white);
        all.add(topPanel,BorderLayout.NORTH);
        all.add(centerPanel, BorderLayout.CENTER);

        add(all);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);     
    }
    

    public static void main(String[] args) {
        new LoadingPanel();
    }

} 