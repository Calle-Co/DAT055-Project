package customer;

import java.util.ArrayList;
import java.util.HashMap;
import global.InfoHolding;
public class BookingController {
    private BookingView view;
    private ArrayList<Seat> seats;
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
                s.setStatus(true);
                view.removeInfo(s.getSeat());
            }
            seats.clear();
            toUpdate();
        });
    }

    public void toPrint(String s){
        System.out.println(s);
}

    public static void toSave(String key){
        String name = info.get(key).getName();
        String age  = info.get(key).getAge();
            InfoHolding nyP = new InfoHolding(name, age, "test");
            people.add(nyP);
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
