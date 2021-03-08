package customer.myBookings;

import java.util.*;
import global.AllButtons;
import java.awt.event.*;
import javax.swing.JButton;
import customer.flight.FlightInfoButton;

/**
 * Kontroller för MyBooking-panelen. Fungerar som en mellanhand mellan
 * MyBookingModel och MyBookingView. Den tar information från den ena och skickar 
 * vidare till den andra.
 * @author Simon Länsberg & William Husar
 * @version 2021-03-04
 */
public class MyBookingController {
    private MyBookingModel model;
    private MyBookingView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<FlightInfoButton> bookings = new ArrayList<>();

    /**
     * Skapar en instans av DestInfoModel och DestInfoView och instanserar en lista av alla knappar från view.
     * @param m En MyBookingModel
     * @param v En MyBookingView
     */
    public MyBookingController(MyBookingModel m, MyBookingView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
        bookings = view.getBookings();
    }
    
    /**
     * Denna metod fungerar som en mellanhand mellan modellen och vyn.
     * Den ser till att model-klassen hämtar hem de bokade flygen för den nuvarande användaren från databasen 
     * sedan skickar den en lista av alla dem hämtade flygen till initbuttons() i vyn. 
     * Den lyssnar även efter knapptryckningar på flygknapparna och initierar isåfall en popupruta i vyn.
     * @param user En användare vars flyg ska visas.
     * @return -1 om exception har förekommit, annars 0.
     */
    public int getBookings(String user){
        try {
            view.initButtons(model.getFlight(model.getBookings(user)));
            addBookingsButtonListener(e -> {
                String s = ((FlightInfoButton) e.getSource()).getFlightID();
                BookingPopup(s, user);
            }); 
        } catch (Exception e) {
            //TODO: handle exception
            // Gör typ som destInfoController.
            return -1;
        }
        return 0;
    }

    /**
     * Denna metod fungerar som en mellanhand mellan modellen och vyn.
     * Den ser till att view visar en popup ruta där användaren kan välja att ta bort en av sina bokningar. 
     * Om användaren klickar på YES så skickas det specifika flyget och användaren till model för att ta bort bokningen från databasen.
     * vidare till model-klassen.
     * @param trip Id för det flyget som ska hanteras.
     * @param user  En användare vars flyg ska tas bort.
     */
    public void BookingPopup(String trip, String user){
        if(view.BookingPopup()){
            try {
                model.deleteBooking(user, trip);
            } catch (Exception e){
                //TODO: handle exception
            }    
        }
        getBookings(user);
    }

    /**
     * Denna metod lägger till en actionListener på alla bokningar som hämtades från view.
     * @param al En actionListener
     */
    public void addBookingsButtonListener(ActionListener al){
        for(FlightInfoButton b : bookings){
            b.addActionListener(al);
        }
    }

    /**
     * Denna metod lägger till en actionListener på alla knappar som hämtades från view.
     * @param al En actionListener
     */
    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}
