package admin;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.*;

import global.AllButtons;

public class ShowFlightsController {
        private ShowFlightsModel model;
        private ShowFlightsView view;
        private ArrayList<AllButtons> buttons = new ArrayList<>();
        private boolean noFlight;
        
    public ShowFlightsController(ShowFlightsModel m, ShowFlightsView v){

        this.model = m;
        this.view = v;
        buttons = view.getButtons();
        
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    public void listAllFlights() {
        try {
            ArrayList<customer.flight.FlightInfoButton> infoButtons = model.getFlights();
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
        buttons = view.getButtons();
    }

    public Boolean getNoFlight(){
        return this.noFlight;
    }

    public void getDestinations(){
        try {
            view.setDestinations(model.getDestinations());
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public boolean addFlight() {
        try{
            model.addFlight(view.getFrom(), view.getTo(), view.getDate(), view.getTime(), view.getModel()); 
        } catch (SQLException e){    
            view.errorPanel();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        view.successPanel();
        return true;
    }

}
