package customer.booking;

import global.*;
import global.AllButtons.size;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import customer.MenuPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Denna klass sköter all grafik och alla JOptionPanes som används i MVC:n bookings
 * @author Simon Länsberg, William Husar
 * @version 2021-03-03
 */
public class BookingView extends JPanel{
    private Seat currentSeat;
    private AllButtons bokaButton;
    private HashMap<String, InfoPanel> info;
    private String text;
    private JPanel sPanel;
    private JPanel iHolder;
    private ArrayList<Seat> seats;
    private ArrayList<AllButtons> buttons;
   
    /**
     * Konstruktormetoden för BookingView.
     * Initsierar alla listor, paneler samt meny knappar som som används i denna klassen
     */
    public BookingView(){
        sPanel = new JPanel();
        iHolder = new JPanel();
        JPanel centerPanel = new JPanel();
        info = new HashMap<>();
        seats = new ArrayList<>();
        buttons = new ArrayList<>();

        bokaButton = new AllButtons(size.MEDIUM, "Boka!");
        AllButtons cancelButton = new AllButtons(size.MEDIUM, "cancel");
        buttons.add(bokaButton);
        buttons.add(cancelButton);

        setLayout(null);
       
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBounds(200, 120, 800, 625);

        sPanel.setBackground(Color.WHITE);
        sPanel.setBorder(new LineBorder(Color.GRAY));
        sPanel.setBounds(20,10,200,600);
        centerPanel.add(sPanel);

        iHolder.setLayout(new GridLayout(4,1,2,2));
        iHolder.setBackground(Color.WHITE);
        iHolder.setBounds(240,10,300,600);
        centerPanel.add(iHolder);

        bokaButton.setBounds(600, 485, 140, 60);
        centerPanel.add(bokaButton);

        cancelButton.setBounds(600, 550, 140, 60);
        centerPanel.add(cancelButton);
        add(centerPanel);
        
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        add(menuPanel);
    }

    /**
     * Metod som adderar en InfoPanel till den scrollbara panelen iHolder. Infopanelen är koppad till det sätet man klickade på.
     * @param s Det valda sätets nummer
     */
    public void addInfo(String s){
            InfoPanel ip = new InfoPanel(s);
            iHolder.add(ip);
            ip.setVisible(true);
            info.put(s,ip);
            iHolder.revalidate();
    }

    /**
     * Motsattsen till "addInfo". Denna metoden tar bort en Infopanel för ett specifikt sätte 
     * @param s Det föredetta valda sätets nummer
     */

    public void removeInfo(String s){
        iHolder.remove(info.get(s));
        info.remove(s);
        iHolder.revalidate();
        iHolder.repaint();
    }

    /**
     * @return returnerar en arraylist med alla säten i
     */
    public ArrayList<Seat> getSeats(){
        return seats;
    }
    /**
     * Initsierar en seats button för varje säte i flygplanet
     * @param n Antalet säten flyget ska ha
     * @param bookedSeats Lista med alla bokade säten så att de initsieras som röda, dvs bokade.
     */
    public void initSeats(int n, ArrayList<String> bookedSeats) {
        clearPanels();
        sPanel.setLayout(new GridLayout((n/4),4));
        for(int i = 1; i <= (n/4); i++) {
            for(int j = 1; j <= 5; j++) {
                if(j == 1) {
                    currentSeat = new Seat(i + "A");
                    sPanel.add(currentSeat);
                    seats.add(currentSeat);
                }
                if(j == 2) {
                    currentSeat = new Seat(i + "B");
                    sPanel.add(currentSeat);
                    seats.add(currentSeat);
                }
                if(j == 3) {
                    JPanel floor = new JPanel();
                    floor.setPreferredSize(new Dimension(20,20));
                    floor.setBackground(Color.WHITE);
                    sPanel.add(floor);
                }
                if(j == 4) {
                    currentSeat = new Seat(i + "C");
                    sPanel.add(currentSeat);
                    seats.add(currentSeat);
                }
                if(j == 5) {
                    currentSeat = new Seat(i + "D");
                    sPanel.add(currentSeat);
                    seats.add(currentSeat);
                }
            }
        }
        for(Seat s : seats) {
            for(String b : bookedSeats) {
                if(b.equals(s.getSeat())) {
                    s.setStatus(true);
                }
            }
        }
        sPanel.revalidate();
        sPanel.repaint();
    }

    /**
     * Rensar alla paneler så att man ska kunna uppdatera dem med ny information
     */
    public void clearPanels() {
        sPanel.removeAll();
        seats.clear();
        iHolder.removeAll();
        info.clear();
        sPanel.revalidate();
        sPanel.repaint();
        iHolder.revalidate();
        iHolder.repaint();
    }

    /**
     * Metoden som sköter skapandet av olika JOptionpanes som presenterar olika medelanden till användare. Vilket medelande som ska visas beror på inparametern "s". 
     * @param s Avgör vilket medelande som ska visas.
     * @return Returnerar sant om Ja eller OK alternativen i fönsterna klickas, Returnerar falskt om Nej alternativet väljs
     */
    public boolean makeOPane(String s){
        if(s.equals("confirm")){
            text = "Are you sure you wish to book your selected seats?";
            int reply = JOptionPane.showConfirmDialog(null, 
            text,
            "Please confirm", 
            JOptionPane.YES_NO_OPTION);
            if( reply == JOptionPane.YES_OPTION){
                return true;
            }
        }
        else if(s.equals("BookingConfirm")){
            JOptionPane.showConfirmDialog(null,
            "The booking has been confirmed. Have a pleasant flight",
            "Success!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE);
            return true;    
        }
        else if(s.equals("SelectError")){
            JOptionPane.showMessageDialog(null, 
                "You need to select a seat and fill the details before you book", 
                "Error!", 
                JOptionPane.WARNING_MESSAGE);
            text = "";
            return true;
        }
        else if(s.equals("SeatOccupiedError")){
            JOptionPane.showMessageDialog(null, 
                "One or more seats have already been booked", 
                "Error!", 
                JOptionPane.WARNING_MESSAGE);
            text = "";
            
            return true;
        }
        return false;
        }

    /**
     * @return returnerar en hashmap som innehåller alla Infopaneler sorterade efter viket säte de tillhör
     */
    public HashMap<String, InfoPanel> getInfo(){
        return info;
    }
    
    /**
     * Adderar en seatListner till varje enskilt säte i flygplanet
     */
    public void addSeatListener(ActionListener al) {
        for(Seat s : seats) {
            s.addActionListener(al);
        }
    }

    /**
     * @return Returnerar en arraylist med alla knappar i bookingview
     */
	public ArrayList<AllButtons> getButtons() {
		return buttons;
	}
}
