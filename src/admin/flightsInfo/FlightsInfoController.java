package admin.flightsInfo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.*;
import global.AllButtons;

/**
 * Denna klass är en kontroller för AdminHome-panelen, sköter kommunkationen mellan 
 * model och view lägger till lyssnare på alla knappar.
 * @author Anna Manfredsson
 * @version 2021-03-04
 */
public class FlightsInfoController {
        private FlightsInfoModel model;
        private FlightsInfoView view;
        private ArrayList<AllButtons> buttons = new ArrayList<>();
        
    /**
     * Skapar en instans av FlightsInfoView och FlightsInfoModel och instanserar en lista av alla knappar från view.
     * @param m En FlightsInfoView.
     * @param v En FlightsInfoModel.
     */    
    public FlightsInfoController(FlightsInfoModel m, FlightsInfoView v){
        this.model = m;
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

    /**
     * Denna metod kallar dels på en metod i model för att hämta hem alla destinationer,
     * och sen skickar den vidare alla dessa till viewn. 
     */
    public void getDestinations(){
        try {
            view.setDestinations(model.getDestinations());
        } catch (Exception e) {
            //Måste tas om hand, men borde aldrig kunna inträffa.
            e.printStackTrace();
        } 
    }

    /**
     * Denna metod kallar dels på en metod i model för att hämta hem alla flygplansmodeler,
     * och sen skickar den vidare alla dessa till viewn. 
     */
    public void getModels(){
        try {
            view.setPlaneModels(model.getPlaneModels());
        } catch (Exception e) {
            //Måste tas om hand, men borde aldrig kunna inträffa. 
            e.printStackTrace();
        } 
    }

    /**
     * Denna metod kallar på addFlight i model med värden som hämtas ifrån viewn. Om något 
     * skulle gå fel i addFlight sså kallar den på errorPanel metoden i view och ett errormeddelande visas
     * för användaren.
     */
    public void addFlight() {
        try{
            model.addFlight(view.getFrom(), view.getTo(), view.getDate(), view.getTime(), view.getPlaneModel()); 
        } catch (SQLException e){    
            view.errorPanel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();     
            //Måste tas om hand, men borde aldrig kunna inträffa. 
        }
        view.successPanel();
    }
}
