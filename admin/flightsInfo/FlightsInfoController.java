package admin.flightsInfo;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.*;

import global.AllButtons;

public class FlightsInfoController {
        private FlightsInfoModel model;
        private FlightsInfoView view;
        private ArrayList<AllButtons> buttons = new ArrayList<>();
        
    public FlightsInfoController(FlightsInfoModel m, FlightsInfoView v){

        this.model = m;
        this.view = v;
        buttons = view.getButtons();
        
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    public void getDestinations(){
        try {
            view.setDestinations(model.getDestinations());
        } catch (Exception e) {
            //Måste tas om hand, men borde aldrig kunna inträffa.
            e.printStackTrace();
        } 
    }

    public void getModels(){
        try {
            view.setPlaneModels(model.getPlaneModels());
        } catch (Exception e) {
            //Måste tas om hand, men borde aldrig kunna inträffa. 
            e.printStackTrace();
        } 
    }

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
