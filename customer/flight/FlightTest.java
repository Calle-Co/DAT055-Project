package customer.flight;

import javax.swing.*;
import java.awt.*;

public class FlightTest {
    public static void main(String[] args) {
        FlightView v = new FlightView();
        FlightModel m = new FlightModel();
        FlightController c = new FlightController(m,v);
        c.flightTest("Gothenburg", "London", "2021-06-20");
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(v, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    
}
