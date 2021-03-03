package admin;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.*;

import global.AllButtons;

public class ShowFlightsController {
        private ShowFlightsModel model;
        private ShowFlightsView view;
        private ArrayList<AllButtons> buttons = new ArrayList<>();
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


   // public void addKeyListener(KeyListener kl) {
     //   JTextField FlightnrField = view.getFlightnrField();
       // FlightnrField.addKeyListener(kl);
    //}

    public boolean addFlight(String flight_id, String from_d, String to_d, String date_of, String time_of){
        try{
            model.addFlight(flight_id, from_d, to_d, date_of, time_of); 
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
