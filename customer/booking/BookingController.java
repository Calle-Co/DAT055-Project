package customer.booking;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

import global.AllButtons;
import global.InfoHolding;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class BookingController {
    private boolean rth = false;
    private BookingView view;
    private BookingModel model;
    private ArrayList<Seat> seats;
    private static String text;
    private static boolean ok = false;
    private static boolean readyForNew = true;
    private static ArrayList<InfoHolding> people = new ArrayList<>();
    private static HashMap<String, InfoPanel> info; 
    private ArrayList<AllButtons> buttons;
    private int np = 0;
    private int limit = 4;

    public BookingController(BookingModel model, BookingView view){
        this.model = model;
        this.view = view;
        buttons = view.getButtons();
        seats = new ArrayList<>();

        
    }

    public void initBooking(){
        info = view.getInfo();
        rth = false;
        if (readyForNew) {
            for(Seat s: seats){
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
                text = "";
                toUpdate();
                readyForNew = true;
                rth = true;

            }
            else{ 
                text = "";
                readyForNew = false;
            }   
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            if(view.makeOPane("SelectError")){}
            }
        }

        public boolean returnToHome(){
            return this.rth;
        }

    public static void toSave(String key){
        String name = info.get(key).getName();
        String age  = info.get(key).getAge();
        if(!name.isEmpty() && !age.isEmpty() && !info.isEmpty()){
            ok = true;
            InfoHolding nyP = new InfoHolding(name, age, "test", key);
            people.add(nyP);
        }
        else{
            ok = false;
        }
    }

    public void setFlight(String flight_id) {
        //model.getSeats...
        view.initSeats(32);
        view.addSeatListener(e -> {
            String s = ((Seat) e.getSource()).getSeat();
            
                if(((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                    view.removeInfo(s);
                    seats.remove((Seat) e.getSource());
                    np--;
                }
                else if(np == limit){
                    Toolkit.getDefaultToolkit().beep();
                    ((Seat) e.getSource()).setFree();
                }
                else if(!((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                    view.addInfo(s);
                    seats.add((Seat) e.getSource());
                    np++;
                }
            });
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public static void toUpdate(){
        String total = "Owner: Test \n";
        for(InfoHolding p : people){
        total += p.toPrint()+ "\n";
        }
        test.updateDisplay(total);
    }

	public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }
}
