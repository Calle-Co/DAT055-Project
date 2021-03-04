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
    private ArrayList<JButton> menuButtons;

    public HelpController(HelpModel m, HelpView v){
        //this.model = m;
        this.view = v;
        menuButtons = view.getMenuButtons();
    }

    public void addMenuButtonListener(ActionListener al) {
        for(JButton b : menuButtons) {
            b.addActionListener(al);
        }
    }
}