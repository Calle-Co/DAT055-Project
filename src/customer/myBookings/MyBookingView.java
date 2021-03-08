package customer.myBookings;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import global.AllButtons;
import customer.MenuPanel;
import customer.flight.FlightInfoButton;

/**
 * Klassen sköter grafiken för MyBookings-delen av programmet.
 * @author Simon Länsberg, William Husar
 * @version 2021-03-04
 */
@SuppressWarnings("serial")
public class MyBookingView extends JPanel {
    private JPanel bookingsPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<FlightInfoButton> flightButtons;
    private JLabel reservations;

    /**
     * Initierar listor och metoder som i sin tur skapar MyBooking-panelen och all dess innehåll.
     */
    public MyBookingView(){
        buttons = new ArrayList<>();
        flightButtons = new ArrayList<>();
        setLayout(null);
        initMenu();
        initBookingsPanel();   
        reservations= new JLabel("Your reservations:");
        reservations.setFont(new Font("Verdana", Font.PLAIN, 20));
        reservations.setAlignmentX(CENTER_ALIGNMENT);
    }  

    /**
     * Skapar huvudpanelen och gör en scrollpanel.
     */
    private void initBookingsPanel() {
        bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        bookingsPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(bookingsPanel);
        scrollPane.setBounds(200, 120, 800, 625);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
    }

    /**
     * Denna metod initierar alla flygknappar i vyn
     * @param infoButtons En lista av knappar med flyg som användaren bokat
     */
    public void initButtons(ArrayList<FlightInfoButton> infoButtons) {
        if(!flightButtons.isEmpty()) {
            flightButtons.clear();
            bookingsPanel.removeAll();
        }
        bookingsPanel.add(Box.createVerticalStrut(10));
        bookingsPanel.add(reservations);
        bookingsPanel.add(Box.createVerticalStrut(10));
        for(FlightInfoButton fib : infoButtons) {
            flightButtons.add(fib);
            fib.setMaximumSize(new Dimension(600,100));
            fib.setAlignmentX(JButton.CENTER_ALIGNMENT);
            bookingsPanel.add(fib);
            bookingsPanel.add(Box.createVerticalStrut(20));
        }
        bookingsPanel.revalidate();
        bookingsPanel.repaint();
    }

    /**
     * Denna metoden skapar menupanelen och lägger till den i vyn.
     */
    public void initMenu() {
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        add(menuPanel);
    }

    /**
     * En metod som popar ut en ruta om användaren vill ta bort ett av sina bokade flyg.
     * @return true om användare trycker på "YES"-knappen, annars false.
     */
    public boolean BookingPopup(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        String s = "Do you want to delete your this trip?";
        int dialogResult = JOptionPane.showConfirmDialog (null, s,"Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            return true;
        } else {
            return false;
        } 
    }

    /**
     * @return En lista med alla knappar som ligger i MyBooking-view.
     */
    public ArrayList<AllButtons> getButtons() { return buttons; }

    /**
     * @return En lista med alla flygknappar som ligger i MyBooking-view.
     */
    public ArrayList<FlightInfoButton> getBookings() { return flightButtons; }


}
