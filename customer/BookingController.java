package customer;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import global.InfoHolding;
import java.awt.*;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class BookingController {
    private BookingView view;
    private ArrayList<Seat> seats;
    private static String text;
    private static boolean ok = false;
    private static boolean readyForNew = true;
    private static ArrayList<InfoHolding> people = new ArrayList<>();
    private static HashMap<String, InfoPanel> info;

    public BookingController(BookingView view){
        this.view = view;
        seats = new ArrayList<>();
        view.addSeatListener(e -> {
        String s = ((Seat) e.getSource()).getSeat();
            if(!((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                view.addInfo(s);
                seats.add((Seat) e.getSource());
            }
            else if(((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                view.removeInfo(s);
                seats.remove((Seat) e.getSource());
            }
        });

        view.addButtonListener(e -> {
        info = view.getInfo();
        if (readyForNew) {
            for(Seat s: seats){
                toSave(s.getSeat());
            }
        }
        if(ok){
            text = "Are you sure you wish to Book";
            int reply = JOptionPane.showConfirmDialog(null, 
            text,
            "OKEY?", 
            JOptionPane.YES_NO_OPTION);

            if( reply == JOptionPane.YES_OPTION){
                for(Seat s: seats){
                    s.setStatus(true);
                    view.removeInfo(s.getSeat());
                }
                seats.clear();
                text = "";
                toUpdate();
                readyForNew = true;
            }
            else if(reply == JOptionPane.NO_OPTION){ 
                text = "";
                readyForNew = false;
            }   
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, 
                "YOU NEED TO SELECT A SEAT AND FILL BOTH BOXES BEFORE YOU BOOK", 
                "OKEY?", 
                JOptionPane.WARNING_MESSAGE);
            text = "";
            }
        });
    }
    public void toPrint(String s){
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

    public void freeSeats(){
        
    }

    public static void toUpdate(){
        String total = "Owner: Test \n";
        for(InfoHolding p : people){
        total += p.toPrint()+ "\n";
        }
        test.updateDisplay(total);
    }
}
