package customer.flight;

import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

/**
 * Klassen används för att representera ett flyg som en knapp så att
 * kunden kan trycka på det flyg som den vill boka.
 * @author Simon Länsberg
 * @version 2021-02-24
 */
@SuppressWarnings("serial")
public class FlightInfoButton extends JButton {
    private String fID;

    /**
     * Konstruktorn används för att skapa en knapp och fylla den
     * med information om flyget med hjälp av flera parametrar.
     * @param fID Unikt id för flyget.
     * @param from_d Avgående destination.
     * @param to_d Ankommande destination.
     * @param date_Of Datum för avgång.
     * @param time_Of Tid för avgång.
     */
    public FlightInfoButton(String fID, String from_d, String to_d, String date_Of, String time_Of) {
        super();
        this.fID = fID;
        JLabel flightNr = new JLabel("Flight " + fID, JLabel.CENTER);
        JLabel dest  = new JLabel(from_d + " -> " + to_d, JLabel.CENTER);
        JLabel date = new JLabel(date_Of + " " + time_Of.substring(0,5), JLabel.CENTER);
        setLayout(new GridLayout(3,1,0,0));
        add(flightNr);
        add(dest);
        add(date);
        setPreferredSize(new Dimension(250,140));
    }

    /**
     * @return Unikt id för flyget.
     */
    public String getFlightID() {
        return this.fID;
    }
}
