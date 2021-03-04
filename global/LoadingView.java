package global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Klassen används för att visuellt representera tillståndet 
 * då programmet laddar.
 * @author Anna Manfredsson
 * @version 2021-02-23
 */
@SuppressWarnings("serial")
public class LoadingView extends JPanel {

    public LoadingView() {
        setSize(600, 600);
        
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

        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.setBorder(new EmptyBorder(150, 0, 0, 0));
        test.setBackground(Color.WHITE);
    
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        test.add(topPanel, BorderLayout.NORTH);
        test.add(centerPanel, BorderLayout.CENTER);
        add(test, BorderLayout.CENTER);
        setVisible(true);
    }
} 