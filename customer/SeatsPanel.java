package customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeatsPanel extends JPanel {


    public SeatsPanel() {
        setPreferredSize(new Dimension(200,600));
        setBackground(Color.WHITE);
        setLayout(new GridLayout(10,4));
        insertSeats();
    }

    public void insertSeats() {
        for(int i = 1; i <= 10; i++) {
            for(int j = 1; j <= 4; j++) {
                if(j == 1) {
                    add(new Seat(i + "A"));
                }
                if(j == 2) {
                    add(new Seat(i + "B"));
                }
                if(j == 3) {
                    add(new Seat(i + "C"));
                }
                if(j == 4) {
                    add(new Seat(i + "D"));
                }
            }
        }
    }
}
