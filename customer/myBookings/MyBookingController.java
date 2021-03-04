package customer.myBookings;

import java.util.*;
import global.AllButtons;
import java.awt.event.*;
import javax.swing.JButton;

import customer.flight.FlightInfoButton;

public class MyBookingController {
    private MyBookingModel model;
    private MyBookingView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<FlightInfoButton> bookings = new ArrayList<>();


    public MyBookingController(MyBookingModel m, MyBookingView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
        bookings = view.getBookings();
    }

    public int getBookings(String user){
        try {
            view.initButtons(model.getFlight(model.getBookings(user)));
            addBookingsButtonListener(e -> {
                String s = ((FlightInfoButton) e.getSource()).getFlightID();
                BookingPopup(s, user);
            }); 
        } catch (Exception e) {
            //TODO: handle exception
            return -1;
        }
        return 0;
    }

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

    public void addBookingsButtonListener(ActionListener al){
        for(FlightInfoButton b : bookings){
            b.addActionListener(al);
        }
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}
