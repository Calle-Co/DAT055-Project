package customer;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * A simple way of drawing a floor for the Airplane
 */
public class Floor extends JPanel{
    public Floor(){
        super();
        setPreferredSize(new Dimension(20,20));
        setBackground(Color.WHITE);
    }
    
}
