package customer.booking;

import global.*;
import global.AllButtons.size;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import customer.MenuPanel;

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
    private String text;
    private JPanel sPanel;
    private JPanel iHolder;
    private JPanel fillPanel;
    private ArrayList<Seat> seats;
    private ArrayList<AllButtons> buttons;
   
    public BookingView(){
        sPanel = new JPanel();
        iHolder = new JPanel();
        fillPanel = new JPanel();
        info = new HashMap<>();
        seats = new ArrayList<>();
        buttons = new ArrayList<>();
        bokaButton = new AllButtons(size.MEDIUM, "Boka!");
        buttons.add(bokaButton);
        initSeats();
        this.setPreferredSize(new Dimension(500,800));
        this.setBorder(new EmptyBorder(6,6,6,6));
        this.setLayout(new BorderLayout(10,10));
        this.setBackground(Color.WHITE);
        iHolder.setLayout(new GridLayout(4,1,2,2));
        iHolder.setPreferredSize(new Dimension(200, 600));
        iHolder.setVisible(true);
        iHolder.setBackground(Color.WHITE);
        fillPanel.setBackground(Color.WHITE);
        fillPanel.setPreferredSize(new Dimension(700,600));
        fillPanel.setVisible(true);
        this.add(sPanel, BorderLayout.WEST);
        this.add(bokaButton, BorderLayout.SOUTH);
        this.add(iHolder, BorderLayout.CENTER);
        this.add(fillPanel, BorderLayout.EAST);
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setPreferredSize(new Dimension(500,100));
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        this.add(menuPanel, BorderLayout.NORTH);
        
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

    public boolean makeOPane(String s){
        if(s.equals("confirm")){
            text = "Are you sure you wish to Book";
            int reply = JOptionPane.showConfirmDialog(null, 
            text,
            "OKEY?", 
            JOptionPane.YES_NO_OPTION);
            if( reply == JOptionPane.YES_OPTION){
                return true;
            }
        }
        else if(s.equals("BookingConfirm")){
            JOptionPane.showConfirmDialog(null,
            "The booking was succesful! Have a horrible day :) ",
            "Bich!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE);
            return true;    
        }
        else if(s.equals("SelectError")){
            JOptionPane.showMessageDialog(null, 
                "YOU NEED TO SELECT A SEAT AND FILL BOTH BOXES BEFORE YOU BOOK", 
                "OKEY?", 
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
    
    public void addButtonListener(ActionListener al){
        bokaButton.addActionListener(al);
    }

    public void addMenuListener(ActionListener al){
        for(AllButtons b : buttons){
            b.addActionListener(al);
        } 
    }

	public ArrayList<AllButtons> getButtons() {
		return buttons;
	}


}
