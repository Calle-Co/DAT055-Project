package customer.booking;

import java.util.ArrayList;
import java.util.HashMap;
import global.AllButtons;
import global.InfoHolding;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Denna klass är huvudansvarig för all logik som inte hanterar grafik i MVC:n booking
 * @author Simon Länsberg, William Husar
 * @version 2021-03-03
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


    /**
     * Konstructor metoden för BookingController initierar en instans med två parametrar model och view. 
     * @param model Modellen som passas med vid konstruktor anropet 
     * @param view  Vyn som passas med vid konstruktor anropet 
     */
    public BookingController(BookingModel model, BookingView view){
        this.model = model;
        this.view = view;
        buttons = view.getButtons();
        seats = new ArrayList<>();
        people = new ArrayList<>();
    }

    /**
     * Void metod för att initiera boknings flödet. Dvs denna metoden kallas på när användaren har valt sitt säte och trycker på boka knappen. 
     * Om inte informationen för sätet har fyllts i skapas en JOption pane med ett varnings medelande.
     */
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

    /**
     * En getter funktion som hämtar statusen på "initBooking" Om den är färdig retuneras true, annars false 
     * @return initBookings status
     */
    public boolean returnToHome() {
        return this.returnToHome;
    }

    /**
     * Denna metod hämtar och sparar undan informationen som förknipas med de bokade sätena 
     */
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

    /**
     * En reset metod som rensar alla listor och paneler som hör ihop med bokningen. Detta görs för att uppdatera paneler och listor med aktuella värden.
     */
    public void reset() {
        seats.clear();
        view.clearPanels();
        people.clear();
        numberOfSeats = 0;
    }

    /**
     * Initierar en "flygplans" panel där man kan välja sina säten. Antalet säten beror på vilken modell planet är av, den informationen passas med vid metodanropet.
     * @param flight_id Flygplanets id, vilket man med hjälp av databasen får ut antalet säten som planet ska ha.
     */
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
            //Heeeey Ohhh  Listen what i saaaaaay ohhhhhh
        }
        
    }

    /**
     * Metoden som skickar information om säten som ska bokas till BookingModel som sedan bokar in dem i systemet.
     * @throws SQLException Om något går fel vid bokningen kastas en Exception för att informera användaren om detta.
     */
    public void setBooked() throws SQLException {
        try {
            model.setBooked(this.people);
            (this.people).clear();
        } catch (Exception e) {
            throw new SQLException();
        }
    }
    /**
     *  Sätter antalet stolar man kan välja i boknings panelen. Antalet bestäms när man söker flyg och skickas med detta metodanropet. 
     * @param limit Antal väljbara stolar för denna bokning.
     */

    public void setLimit(int limit){
        this.limit = limit;
    }
    
    /**
     * Sätter vem den aktuella användaren är.
     * @param user Nya användaren.
     */
    public void setUser(String user){
        this.user = user;
    }

    /**
     * Lägger till en actionlistnener på ArrayListan buttons.
     * @param al ActionListener.
     */
	public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }

    /**
     * @return  Flygplans ID för denna bokningen.
     */
    public String getFID() {
        return this.flight_id;
    }
}
