package admin.adminHome;

import javax.swing.JButton;
import global.AllButtons;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Denna klass är en kontroller för AdminHome-panelen, sköter kommunkationen mellan 
 * model och view lägger till lyssnare på alla knappar.
 * @author Anna Manfredsson
 * @version 2021-03-03
 */
public class AdminHomeController {
    
    private AdminHomeView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    /**
     * Skapar en instans av AdminHomeView och instanserar en lista av alla knappar från view.
     * @param v En AdminHomeView
     */
    public AdminHomeController(AdminHomeView v){
        this.view = v;
        buttons = view.getButtons();
    }

    /**
     * Lägger till en actionListener på alla knappar som hämtades från view.
     * @param al actionListener som läggs till.
     */
    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}
