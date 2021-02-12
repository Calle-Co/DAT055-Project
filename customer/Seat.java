package customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Seat extends JButton implements ActionListener {
    private String seat;
    private Boolean booked;
    private Boolean clicked;

    public Seat(String seat) {
        super(seat);
        //setBackground(Color.GREEN);
        setPreferredSize(new Dimension(25,25));
        this.seat = seat;
        this.booked = false;
        this.clicked = false;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.clicked = !this.clicked;
        if(this.clicked) {
            paint(Color.CYAN);
        } else {
            paint(Color.GREEN);
        }
    }

    public void colorStatus() {
        if(booked) {
            paint(Color.RED);
        } else {
            paint(Color.GREEN);
        }
    }

    public void paint(Color c) {
        //setBackground(c);
        repaint();
    }

    public void setStatus(Boolean status) {
        this.booked = status;
        colorStatus();
    }

    public Boolean getStatus() {
        return this.booked;
    }

    public String getSeat() {
        return this.seat;
    }
}
