package customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import global.InfoHolding;
import java.awt.*;
public class BookingController {
    private BookingView view;
    private ArrayList<Seat> seats;
    private static boolean ok = false;
    private static ArrayList<InfoHolding> people = new ArrayList<>();
    private static HashMap<String, InfoPanel> info;

    public BookingController(BookingView view){
        this.view = view;
        seats = new ArrayList<>();
        view.addSeatListener(e -> {
        String s = ((Seat) e.getSource()).getSeat();
            if(!((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                System.out.println("yeåå");
                view.addInfo(s);
                seats.add((Seat) e.getSource());
            }
            else if(((Seat) e.getSource()).getClick() && !((Seat) e.getSource()).getStatus()){
                System.out.println("neåå");
                view.removeInfo(s);
                seats.remove((Seat) e.getSource());
            }
        });

        view.addButtonListener(e -> {
        info = view.getInfo();
        for(Seat s: seats){
            toSave(s.getSeat());
        }
        if(ok){
            String text = "Are you sure you whish to Book the following seats? \n";
            Iterator<InfoHolding> iter = people.iterator(); 
            while(iter.hasNext()){
                InfoHolding tmp = iter.next();
                text += tmp.getSeat();
            }
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
            }
            else if(reply == JOptionPane.NO_OPTION){ 
                seats.clear();
                text = "";
                toUpdate();
            }
            
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, 
                "YOU NEED TO SELECT A SEAT AND FILL BOTH BOXES BEFORE YOU BOOK", 
                "OKEY?", 
                JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public void toPrint(String s){
    System.out.println(s);
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

    public static void toUpdate(){
        String total = "Owner: Test \n";
        for(InfoHolding p : people){
        total += p.toPrint()+ "\n";
        }
        System.out.println(total);
        test.updateDisplay(total);
    }
}
