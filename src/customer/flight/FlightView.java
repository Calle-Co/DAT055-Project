package customer.flight;

import global.*;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import customer.MenuPanel;
import java.awt.*;

/**
 * Klassen representerar view i det MVC som ansvarar
 * för att hämta och visa flyg som passar kundens önskemål.
 * @author Simon Länsberg, William Husar
 * @version 2021-03-02
 */
@SuppressWarnings("serial")
public class FlightView extends JPanel {
    private ArrayList<FlightInfoButton> flightButtons;
    private ArrayList<JButton> menuButtons;
    private JPanel list;

    public FlightView() {
        menuButtons = new ArrayList<>();
        flightButtons = new ArrayList<>();
        
        setLayout(null);
        
        list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(200, 120, 800, 625);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
        
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            menuButtons.add(b);
        }
        add(menuPanel);
    }

    /**
     * Metoden skapar en pop-up panel om kundens sökresultat blev tomt.
     * @return Sann när användaren fått meddelandet.
     */
    public boolean makeOPane() {
            JOptionPane.showConfirmDialog(null,
            "There is no such flight! We apologize for the inconvenience",
            "Sorry!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE);
            return true;    
    }

    /**
     * Metoden lägger in alla flyg från kundens sökresultat i en panel.
     * @param infoButtons Lista med knappar som representerar flyg.
     */
    public void initButtons(ArrayList<FlightInfoButton> infoButtons) {
        if(!flightButtons.isEmpty()) {
            flightButtons.clear();
            list.removeAll();
        }
        JLabel choose = new JLabel("Choose a flight:");
        choose.setFont(new Font("Verdana", Font.PLAIN, 20));
        choose.setAlignmentX(CENTER_ALIGNMENT);
        list.add(Box.createVerticalStrut(10));
        list.add(choose);
        list.add(Box.createVerticalStrut(10));
        for(FlightInfoButton fib : infoButtons) {
            flightButtons.add(fib);
            fib.setMaximumSize(new Dimension(600,100));
            fib.setAlignmentX(JButton.CENTER_ALIGNMENT);
            list.add(fib);
            list.add(Box.createVerticalStrut(20));
        }
        list.revalidate();
        list.repaint();
    }

    /**
     * @return En lista med alla menyknappar.
     */
    public ArrayList<JButton> getMenuButtons() { return menuButtons; }

    /**
     * @return En lista med alla knappar som representerar flyg.
     */
    public ArrayList<FlightInfoButton> getFlightButtons() { return flightButtons; }
}
