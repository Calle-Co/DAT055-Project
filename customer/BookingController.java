package customer;

import java.util.ArrayList;

public class BookingController {
    private BookingView view;
    private ArrayList<Seat> seats;

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
            for(Seat s: seats){
                s.setStatus(true);
                view.removeInfo(s.getSeat());
            }
            seats.clear();
        });
    }
}
