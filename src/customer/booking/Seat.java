package customer.booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Denna klass representerar de olika sätenas iconer och deras funktionalitet 
 * @author Simon Länsberg, William Husar
 * @version 2021-02-15
 */
@SuppressWarnings("serial")
public class Seat extends JButton implements ActionListener{
    private String seat;
    private Boolean booked;
    private Boolean clicked;
    private ImageIcon click = new ImageIcon("src/customer/resources/click.PNG");
    private ImageIcon free  = new ImageIcon("src/customer/resources/free.PNG");
    private ImageIcon book  = new ImageIcon("src/customer/resources/booked.PNG");

    /**
     * Denna konstruktor skapar ett säte med ett namn ( sätes numert ex. 1a) och ger de andra instansvariablerna sina start värden 
     * @param seat Detta är sätes nummer/namn
     */
    public Seat(String seat) {
        super();
        this.paint(free);
        //setBackground(Color.GREEN);
        setPreferredSize(new Dimension(25,25));
        setBorderPainted(false);
        setContentAreaFilled(false);
        this.seat = seat;
        this.booked = false;
        this.clicked = false;
        addActionListener(this);
    }

    /**
     * Denna metoden undersöker om ett säte har blivit valt och i så fall så byter det färg på sätet till turkos så läge som det vala sätet inte redan var valt. Om detta är fallet så byter stolen till grön istället. 
     * Om stolen är röd, dvs bokad så kommer ett error ljud spelas när man försöker trycka på stolen.
     */
    public void actionPerformed(ActionEvent e) {
        this.clicked = !this.clicked;
        if(this.clicked && !booked) {
            this.paint(click);
        } 
        else if(this.booked){
            Toolkit.getDefaultToolkit().beep();
        }
        else {
            this.paint(free);
        }
    }

    /**
     * Denna metoden används för att sätta sätet till rött (Bokat) och nollställer sätet till grönt om det avbokas.
     */
    public void colorStatus() {
        if(booked) {
            this.paint(book);
            this.clicked = !this.clicked;
        } else {
            this.paint(free);
        }
    }

    /**
     * Metoden som sköter ikon bytet
     */
    public void paint(ImageIcon c) {
        this.setIcon(c);
        repaint();
    }

    /**
     * Detta är en metod för att sätta statusen för ett säte
     * @param status Boolean: True/False, Sätets status
     */
    public void setStatus(Boolean status) {
        this.booked = status;
        this.clicked = false;
        colorStatus();
    }

    /**
     * @return Boolean, Sätets status
     */
    public Boolean getStatus() {
        return this.booked;
    }

    /**
     * @return String, Sätets namn
     */
    public String getSeat() {
        return this.seat;
    }

    /**
     * @return True om sätet är valt, annars false
     */
    public Boolean getClick(){
        return this.clicked;
    }
    
    /**
     * Sätter sätet till "free" läget, dvs redo att klickas på
     */
    public void setFree(){
        this.paint(free);
        this.clicked = !clicked;
    }
}