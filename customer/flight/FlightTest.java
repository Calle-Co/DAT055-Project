package customer.flight;

import javax.swing.*;
import java.awt.*;

public class FlightTest {
    public static void main(String[] args) {
        FlightView v = new FlightView();
        FlightController c = new FlightController(v);
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(v, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    
}
