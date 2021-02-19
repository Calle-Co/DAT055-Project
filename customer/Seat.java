package customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This is a Class for representing the seats icons and their functionality
 */

public class Seat extends JButton implements ActionListener{
    private String seat;
    private Boolean booked;
    private Boolean clicked;
    private ImageIcon click = new ImageIcon("customer/Resources/click.PNG");
    private ImageIcon free  = new ImageIcon("customer/Resources/free.PNG");
    private ImageIcon book  = new ImageIcon("customer/Resources/booked.PNG");

    /**
     * This is the constructor for the seats. It initialises each seat with a name (the seats number ex. 1a) and toggels the other instance-variabels to their start values 
     * @param seat This is the name/number of this seat.
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
     * This method detects if a seat is selected and if so changes the icon to a cyan seat, if the seat was not previously selected. Otherwise the seat-icon is set to the free. If the seat is booked a error sound is played
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
     * This is the method used to set the booked seat-icon, and reset to the free icon if the seat is canceled 
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
     * This is the icon swaping method. It was created to minimise code repeting
     */
    public void paint(ImageIcon c) {
        this.setIcon(c);
        repaint();
    }

    /**
     * This is a setter for setting this seats status.
     * @param status Booleand: True/False,  The new status for the seat.
     */
    public void setStatus(Boolean status) {
        this.booked = status;
        colorStatus();
    }

    /**
     * This is a getter for retriving this seats booking status. A boolean is returned.
     */
    public Boolean getStatus() {
        return this.booked;
    }

    /**
     * This is a getter for this seats name/number, a String is returned.
     */
    public String getSeat() {
        return this.seat;
    }
    public Boolean getClick(){
        return this.clicked;
    }
}
