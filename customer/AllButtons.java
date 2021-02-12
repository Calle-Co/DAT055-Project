package customer;

import java.awt.Dimension;
import javax.swing.JButton;



public class AllButtons extends JButton{
    
    public enum size{
        SMALL,
        MEDIUM,
        LARGE
    }

    public AllButtons(size s, String title){
        
        super(title);

        if(s == size.SMALL){
            setPreferredSize(new Dimension(80,40));
        }
        else if(s == size.MEDIUM){

        }
        else if(s == size.LARGE){
            setPreferredSize(new Dimension(240, 80));
        }
    }
    
}
