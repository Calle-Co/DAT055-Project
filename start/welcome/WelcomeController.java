package start.welcome;

import javax.swing.JButton;
import global.AllButtons;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Denna klassen sköter kommunikation mellan WelcomeView och resten av programmet.
 * @author William Husar, Simon Länsberg
 * @version 2021-03-03
 */
public class WelcomeController {
    private WelcomeView view;
    private ArrayList<AllButtons> buttons;

    /**
     * Skapar en instans av WelcomeView och instanserar en lista av alla knappar från view.
     * @param v En WelcomeView
     */
    public WelcomeController(WelcomeView v){
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