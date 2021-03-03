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
    //private JPanel fillPanel;
    //private JPanel centerPanel;
    private ArrayList<Seat> seats;
    private ArrayList<AllButtons> buttons;
   
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

    public void addInfo(String s){
            InfoPanel ip = new InfoPanel(s);
            iHolder.add(ip);
            ip.setVisible(true);
            info.put(s,ip);
            iHolder.revalidate();
    }

    public void removeInfo(String s){
        iHolder.remove(info.get(s));
        info.remove(s);
        iHolder.revalidate();
        iHolder.repaint();
    }

    public ArrayList<Seat> getSeats(){
        return seats;
    }

    public void initSeats(int n) {
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
        sPanel.revalidate();
        sPanel.repaint();
    }

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
        return false;
        }

    public HashMap<String, InfoPanel> getInfo(){
        return info;
    }
    
    public void addSeatListener(ActionListener al) {
        for(Seat s : seats) {
            s.addActionListener(al);
        }
    }

	public ArrayList<AllButtons> getButtons() {
		return buttons;
	}
}
