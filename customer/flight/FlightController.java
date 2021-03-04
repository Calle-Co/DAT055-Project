package customer.flight;

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * Klassen representerar controllern i det MVC som ansvarar
 * för att hämta och visa flyg som passar kundens önskemål.
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class FlightController {
    private FlightView view;
    private FlightModel model;
    private ArrayList<JButton> menuButtons;
    private boolean noFlight;

    /**
     * Konstruktorn skapar en controller med hjälp
     * av två parametrar.
     * @param model Model från samma MVC.
     * @param view View från samma MVC.
     */
    public FlightController(FlightModel model, FlightView view) {
        this.model = model;
        this.view = view;
        menuButtons = view.getMenuButtons();
    }

    /**
     * Metoden används för att hämta flyg som passar kundens önskemål
     * och skickar vidare dessa.
     * @param from Avgående destination.
     * @param to Ankommande destination.
     * @param date Datum för avgång.
     */
    public void flightSearch(String from, String to, String date) {
        try {
            ArrayList<FlightInfoButton> infoButtons =  model.getFlight(from, to, date);
             if(infoButtons == (null)){
                this.noFlight = true;
             }
             else {
                view.initButtons(infoButtons);
                this.noFlight = false;
             }
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
    }

    /**
     * @return Sann om sökningen ej gav något resultat, annars falsk.
     */
    public Boolean getNoFlight() {
        return this.noFlight;
    }

    /**
     * Metoden lägger till en lyssnare på respektive flyg.
     * @param al En lyssnare.
     */
    public void addFlightButtonListener(ActionListener al) {
        for(JButton b : view.getFlightButtons()) {
            b.addActionListener(al);
        }
    }

    /**
     * Metoden lägger till en lyssnare på respektive menyknapp.
     * @param al En lyssnare.
     */
    public void addMenuButtonListener(ActionListener al) {
        for(JButton b : menuButtons) {
            b.addActionListener(al);
        }
    }
}
