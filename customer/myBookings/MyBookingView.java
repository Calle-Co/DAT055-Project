package customer.myBookings;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

import global.AllButtons;
import global.Flight;
import customer.MenuPanel;
import customer.flight.FlightInfoButton;

@SuppressWarnings("serial")
public class MyBookingView extends JPanel{
    private JPanel bookingsPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<FlightInfoButton> flightButtons;

    public MyBookingView(){
        buttons = new ArrayList<>();
        flightButtons = new ArrayList<>();
        setLayout(null);
        initMenu();
        initBookingsPanel();   
    }  

    private void initBookingsPanel() {
        bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        bookingsPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(bookingsPanel);
        scrollPane.setBounds(200, 120, 800, 625);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
    }

    public void initButtons(ArrayList<FlightInfoButton> infoButtons) {
        if(!flightButtons.isEmpty()) {
            flightButtons.clear();
            bookingsPanel.removeAll();
        }
        JLabel reservations = new JLabel("Your reservations:");
        reservations.setFont(new Font("Verdana", Font.PLAIN, 20));
        reservations.setAlignmentX(CENTER_ALIGNMENT);
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

    public void initMenu() {
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        add(menuPanel);
    }

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


    public ArrayList<AllButtons> getButtons() { return buttons; }
    public ArrayList<FlightInfoButton> getBookings() { return flightButtons; }


}
