package customer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import global.InfoHolding;
import java.awt.*;
public class BookingController {
    private BookingView view;
    private ArrayList<Seat> seats;
    private static boolean ok = true;
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
            for(Seat s: seats){
                s.setStatus(true);
                view.removeInfo(s.getSeat());
            }
            seats.clear();
            toUpdate();
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, 
                "YOU NEED HELP", 
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
        if(!name.isEmpty() && !age.isEmpty()){
            ok = true;
            InfoHolding nyP = new InfoHolding(name, age, "test");
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
