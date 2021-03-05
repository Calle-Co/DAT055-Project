package customer.help;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;

/**
 * 
 * @author Kevin Hao
 * @version 2021-03-05
 */
@SuppressWarnings("serial")
public class EmptyLabel extends JTextArea{
    public EmptyLabel(int rows){
        setRows(rows);
        setEditable(false);
        setBackground(Color.white);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
}
