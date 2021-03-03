package admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import customer.flight.FlightInfoButton;
import global.AllButtons;

public class ShowFlightsView extends JPanel {
    private JPanel contentPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<AllButtons> flights = new ArrayList<>();
    private ArrayList<FlightInfoButton> myList;
    private JPanel list;
    private JTextField flight_id;
    private JTextField from;
    private JTextField to;
    private JTextField date;
    private JTextField time;
    

    public ShowFlightsView(){
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(20,1));

        AllButtons updateButton = new AllButtons(AllButtons.size.LARGE, "Update");
        buttons.add(updateButton);
        add(updateButton);

        AdminMenuPanel menuPanel = new AdminMenuPanel();
        for (AllButtons b : menuPanel.getButtons()){
            buttons.add(b);
        }

        list = new JPanel();
        list.setLayout(new GridLayout(20,1,10,10));
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300,600));
        myList = new ArrayList<>();
        //add(new JScrollPane(list));
        

        JPanel addPanel = new JPanel();
        JLabel addFlight = new JLabel("Add a flight");

        flight_id = new JTextField("flightname");
        from = new JTextField("from");
        to = new JTextField("to");
        date = new JTextField("date");
        time = new JTextField("time");
        AllButtons addFlightsButton = new AllButtons(AllButtons.size.MEDIUM, "Add Flight");
        buttons.add(addFlightsButton);
    
        addPanel.setLayout(new GridLayout(3, 2, 20, 20));
        addPanel.setBorder(new EmptyBorder(20, 200, 50, 200));
        addPanel.add(addFlight);
        addPanel.add(flight_id);
        addPanel.add(from);
        addPanel.add(to);
        addPanel.add(date);
        addPanel.add(time);
        addPanel.add(addFlightsButton);
        //addPanel.setVisible(true);

        setLayout(new BorderLayout());
        add(menuPanel,BorderLayout.NORTH);
        //add(new JScrollPane(list), BorderLayout.CENTER);
        add(addPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void errorPanel(){
        //Liten beep :)
        java.awt.Toolkit.getDefaultToolkit().beep();
        String s = "That flight already exist!\n";
        JOptionPane.showMessageDialog(this, s, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    public void successPanel(){
        String s = "Flight added!\n";
        JOptionPane.showMessageDialog(this, s, "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getFlightnr(){
        return flight_id.getText();
    }
    public String getFrom(){
        return from.getText();
    }
    public String getTo(){
        return to.getText();
    }
    public String getDate(){
        return date.getText();
    }

    public String getTime(){
        return time.getText();
    }
    //public JTextField getPasswordField() { return password; }
    public ArrayList<AllButtons> getButtons(){ return buttons; }
    
}




