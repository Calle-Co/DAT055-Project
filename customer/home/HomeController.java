package customer.home;

import global.AllButtons;
import global.Destination;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Denna klass är en kontroller för Home-panelen, sköter kommunkationen mellan 
 * model och view lägger till lyssnare på alla knappar.
 * @author Simon Länsberg & William Husar
 * @version 2021-02-26
 */
public class HomeController {
    private HomeModel model;
    private HomeView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    /**
     * Skapar en instans av HomeView och HomeoModel och instanserar en lista av alla knappar från view.
     * @param m En HomeView.
     * @param v En HomeModel.
     */  
    public HomeController(HomeModel m, HomeView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    /**
     * Denna metod fungerar som en mellanhand mellan modellen och vyn.
     * Den ser till att model-klassen hämtar hem destinationerna från databasen
     * och så skickar den en lista av alla destinationerna till setDestination() i vyn.
     * Den rensar även upp mellan anropen så att det inte blir duplicerade destinationer.
     */
    public void getDestinations(){
        try {
            ArrayList<Destination> destinations = model.getDestinations();
            view.setDestinations(destinations);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

     /**
     * Denna metod lägger till en actionListener på alla knappar som hämtades från view.
     * @param al En actionListener
     */
    public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }
}
