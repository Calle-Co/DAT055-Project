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
    private ArrayList<JButton> buttons;
    private boolean noFlight;

    public FlightController(FlightModel model, FlightView view){
        this.model = model;
        this.view = view;
        buttons = new ArrayList<>();
    }

    public void flightTest(String from, String to, String date) {
        try {
            ArrayList<FlightInfoButton> infoButtons =  model.getFlight(from, to, date);
             if(infoButtons == (null)){
                this.noFlight = true;
             }
             else {
                view.initButtons(infoButtons);
                this.noFlight = false;
             }
            //view.initButtons(new FlightInfoButton(s[0], s[1], s[2], s[3], s[4]));
            
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
        buttons = view.getButtons();
    }

    public Boolean getNoFlight(){
        return this.noFlight;
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}
