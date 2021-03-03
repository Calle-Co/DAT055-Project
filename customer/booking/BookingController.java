package customer.booking;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.text.View;

import global.AllButtons;
import global.InfoHolding;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class BookingController {
    private boolean returnToHome = false;
    private BookingView view;
    private BookingModel model;
    private ArrayList<Seat> seats;
    private boolean ok = false;
    private boolean readyForNew = true;
    private ArrayList<InfoHolding> people;
    private HashMap<String, InfoPanel> info; 
    private ArrayList<AllButtons> buttons;
    private int numberOfSeats = 0;
    private int limit = 4;
    private String user;
    private String flight_id;

    public BookingController(BookingModel model, BookingView view){
        this.model = model;
        this.view = view;
        buttons = view.getButtons();
        seats = new ArrayList<>();
        people = new ArrayList<>();
    }

    public void initBooking() {
        info = view.getInfo();
        returnToHome = false;
        if (readyForNew) {
            for(Seat s: seats) {
                toSave(s.getSeat());
            }
        }
        if(ok){
            if(view.makeOPane("confirm")){
                for(Seat s: seats){
                    s.setStatus(true);
                    view.removeInfo(s.getSeat());
                }
                seats.clear();
                readyForNew = true;
                returnToHome = true;

            }
            else{ 
                readyForNew = false;
            }   
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            if(view.makeOPane("SelectError")){}
            }
    }

    public boolean returnToHome() {
        return this.returnToHome;
    }

    public void toSave(String key){
        String name = info.get(key).getName();
        String age  = info.get(key).getAge();
        if(!name.isEmpty() && !age.isEmpty() && !info.isEmpty()){
            ok = true;
            InfoHolding nyP = new InfoHolding(name, age, user, key, this.flight_id);
            people.add(nyP);
        }
        else{
            ok = false;
        }
    }

    public void reset() {
        seats.clear();
        view.clearPanels();
        people.clear();
        numberOfSeats = 0;
    }

    public void setFlight(String flight_id) {
        this.flight_id = flight_id;
        try {
            view.initSeats(model.getSeatsNo(flight_id), model.getBookedSeats(flight_id));
            view.addSeatListener(e -> {
            String s = ((Seat) e.getSource()).getSeat();
            
                if(((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                    view.removeInfo(s);
                    seats.remove((Seat) e.getSource());
                    numberOfSeats--;
                }
                else if(numberOfSeats == limit){
                    Toolkit.getDefaultToolkit().beep();
                    ((Seat) e.getSource()).setFree();
                }
                else if(!((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                    view.addInfo(s);
                    seats.add((Seat) e.getSource());
                    numberOfSeats++;
                }
            });
        } catch (Exception e) {
            //bruh
        }
        
    }

    public void setBooked() throws SQLException {
        try {
            model.setBooked(this.people);
            (this.people).clear();
        } catch (Exception e) {
            throw new SQLException();
        }
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public void setUser(String user){
        this.user = user;
    }

	public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }

    public String getFID() {
        return this.flight_id;
    }
}
