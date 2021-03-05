package customer.help;

import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.*;

/**
 * @author Kevin Hao
 * @version 2021-03-03
 */
public class HelpController {
    //private HelpModel model;
    private HelpView view;
    private ArrayList<JButton> buttons;

    public HelpController(HelpModel m, HelpView v){
        //this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}