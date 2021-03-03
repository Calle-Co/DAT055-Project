package customer.flight;

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;

import global.AllButtons;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class FlightController {
    private FlightView view;
    private FlightModel model;
    private ArrayList<JButton> menuButtons;
    private boolean noFlight;

    public FlightController(FlightModel model, FlightView view){
        this.model = model;
        this.view = view;
        menuButtons = view.getMenuButtons();
    }

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

    public Boolean getNoFlight(){
        return this.noFlight;
    }

    public void addFlightButtonListener(ActionListener al) {
        for(JButton b : view.getFlightButtons()) {
            b.addActionListener(al);
        }
    }

    public void addMenuButtonListener(ActionListener al) {
        for(JButton b : menuButtons) {
            b.addActionListener(al);
        }
    }
}
