package customer.booking;

import global.*;
import global.AllButtons.size;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class BookingView extends JPanel{
    private Seat currentSeat;
    private AllButtons bokaButton;
    private HashMap<String, InfoPanel> info;
    private JPanel sPanel;
    private JPanel iHolder;
    private ArrayList<Seat> seats;
   
    public BookingView(){
        bokaButton = new AllButtons(size.MEDIUM, "Boka!");
        sPanel = new JPanel();
        iHolder = new JPanel();
        info = new HashMap<>();
        seats = new ArrayList<>();
        initSeats();
        this.setPreferredSize(new Dimension(500,800));
        this.setBorder(new EmptyBorder(6,6,6,6));
        this.setLayout(new BorderLayout(10,10));
        this.setBackground(Color.WHITE);
        iHolder.setLayout(new GridLayout(4,1,2,2));
        iHolder.setPreferredSize(new Dimension(200, 600));
        iHolder.setVisible(true);
        iHolder.setBackground(Color.WHITE);
        //this.setLayout(new BorderLayout());
        this.add(sPanel, BorderLayout.WEST);
        this.add(bokaButton, BorderLayout.SOUTH);
        this.add(iHolder, BorderLayout.CENTER);
        
    }

    public void addInfo(String s){
        InfoPanel ip = new InfoPanel(s);
        iHolder.add(ip);
        ip.setVisible(true);
        System.out.println(s);
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

    public void initSeats(){
        sPanel.setPreferredSize(new Dimension(200,600));
        sPanel.setBackground(Color.WHITE);
        sPanel.setLayout(new GridLayout(10,4));
        sPanel.setVisible(true);
        for(int i = 1; i <= 10; i++) {
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
    }

    public HashMap<String, InfoPanel> getInfo(){
        return info;
    }
    
    public void addSeatListener(ActionListener al) {
        for(Seat s : seats) {
            s.addActionListener(al);
        }
    }
    
    public void addButtonListener(ActionListener al){
        bokaButton.addActionListener(al);
    }
}
