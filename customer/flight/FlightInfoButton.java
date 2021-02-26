package customer.flight;

import java.awt.Dimension;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class FlightInfoButton extends JButton {
    private String fID;
    private String from_d;
    private String to_d;
    private String date_Of;
    private String time_Of;

    public FlightInfoButton(){
        super();
        fID = "420";
        from_d = "GBG";
        to_d = "AMS";
        date_Of = "2021-04-20";
        JLabel flightNr = new JLabel("Flight " + fID, JLabel.CENTER);
        JLabel dest  = new JLabel(from_d + " -> " + to_d, JLabel.CENTER);
        JLabel date_of = new JLabel(date_Of, JLabel.CENTER);
        JLabel time_of = new JLabel(time_Of, JLabel.CENTER);
        JPanel date = new JPanel();
        date.setLayout(new GridLayout(1,2,0,0));
        date.add(date_of);
        date.add(time_of);

        this.setLayout(new GridLayout(3,1,0,0));
        this.add(flightNr);
        this.add(dest);
        this.add(date);
        setPreferredSize(new Dimension(250,140));
    }

    public FlightInfoButton(String fID, String from_d, String to_d, String date_Of, String time_Of){
        super();
        this.fID = fID;
        this.from_d = from_d;
        this.to_d = to_d;
        this.date_Of = date_Of;
        this.time_Of = time_Of;
        JLabel flightNr = new JLabel("Flight " + fID, JLabel.CENTER);
        JLabel dest  = new JLabel(from_d + " -> " + to_d, JLabel.CENTER);
        JLabel date = new JLabel(date_Of + " " + time_Of.substring(0,5), JLabel.CENTER);

        this.setLayout(new GridLayout(3,1,0,0));
        this.add(flightNr);
        this.add(dest);
        this.add(date);
        setPreferredSize(new Dimension(250,140));
    }

    public String getFlightID(){
        return this.fID;
    }
    
}
