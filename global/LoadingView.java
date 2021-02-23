package global;

import javax.swing.*;
import java.awt.*;

public class LoadingView extends JPanel {

    public LoadingView(){
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        JLabel label = new JLabel("Loading...");
        topPanel.add(label);
        topPanel.setVisible(true);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        //put gif as icon in an imageIcon
        ImageIcon imageIcon = new ImageIcon("global/Resources/loading.gif");
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(imageIcon);
        centerPanel.add(iconLabel);
        centerPanel.setVisible(true);
    
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
          
    }
} 