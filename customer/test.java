package customer;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        BookingView v = new BookingView();
        BookingController c = new BookingController(v);
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(v, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
