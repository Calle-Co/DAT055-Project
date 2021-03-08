package customer.help;

import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.*;

/**
 * @author Kevin Hao
 * @version 2021-03-03
 */
public class HelpController {
    private HelpView view;
    private ArrayList<JButton> buttons;

    /**
     * Skapar en instans av HelpView och instanserar en lista av alla knappar från view.
     * @param v En HelpView
     */
    public HelpController(HelpView v){
        this.view = v;
        buttons = view.getButtons();
    }

    /**
     * Denna metod lägger till en actionListener på alla knappar som hämtades från view.
     * @param al En actionListener
     */
    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}