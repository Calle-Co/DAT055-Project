package admin;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;

import customer.flight.FlightInfoButton;
import global.*;

public class ShowFlightsView extends JPanel {
    private JPanel destPanel1;
    private JPanel destPanel2;
    private JPanel addPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<FlightInfoButton> flightButtons;
    private String[] choices;
    private JComboBox combobox1;
    private JComboBox combobox2;
    private JPanel showflightsPanel;
    private JLabel from;
    private JLabel to;
    private JTextField date;
    private JTextField time;
    private JTextField model;
    

    public ShowFlightsView(){

        setLayout(new BorderLayout());
       
    //------------------MENU PANEL--------------------
        AdminMenuPanel menuPanel = new AdminMenuPanel();
        for (AllButtons b : menuPanel.getButtons()){
            buttons.add(b);
        }
        add(menuPanel,BorderLayout.NORTH);


    //---------------SHOW FLIGHTS--------------------
        flightButtons = new ArrayList<>();
        
        showflightsPanel = new JPanel();
        showflightsPanel.setLayout(new BoxLayout(showflightsPanel, BoxLayout.Y_AXIS));
        showflightsPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(showflightsPanel);
        scrollPane.setBounds(200, 120, 800, 625);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        //contentPanel = new JPanel();
        //contentPanel.setLayout(new GridLayout(20,1));

    //---------------ADD FLIGHTS-----------------------
        addPanel = new JPanel();
        JLabel addFlight = new JLabel("Add a flight");
       
        from = new JLabel("from");
        to = new JLabel("to");
        date = new JTextField("date");
        time = new JTextField("time");
        model = new JTextField("model");
        
        AllButtons addFlightsButton = new AllButtons(AllButtons.size.MEDIUM, "Add Flight");
        buttons.add(addFlightsButton);

        destPanel1 = new JPanel();
        destPanel1.setLayout(null);
        destPanel2 = new JPanel();
        destPanel2.setLayout(null);
        destPanel1.add(from);
        destPanel2.add(to);
       // destPanel1.setVisible(true);
       // destPanel2.setVisible(true);

        addPanel.setLayout(new GridLayout(3, 3, 20, 20));
        addPanel.setBorder(new EmptyBorder(20, 200, 50, 200));
        addPanel.add(addFlight);
        addPanel.add(destPanel1);
        addPanel.add(destPanel2);
        addPanel.add(date);
        addPanel.add(time);
        addPanel.add(model);
        addPanel.add(addFlightsButton);
        //addPanel.setVisible(true);

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

    public void initButtons(ArrayList<FlightInfoButton> infoButtons) {
        if(!flightButtons.isEmpty()) {
            flightButtons.clear();
            showflightsPanel.removeAll();
        }
        showflightsPanel.add(Box.createVerticalStrut(20));
        for(FlightInfoButton fib : infoButtons) {
            flightButtons.add(fib);
            fib.setMaximumSize(new Dimension(600,100));
            fib.setAlignmentX(JButton.CENTER_ALIGNMENT);
            showflightsPanel.add(fib);
            showflightsPanel.add(Box.createVerticalStrut(20));
            //buttons.add(fib);
        }
        showflightsPanel.revalidate();
        showflightsPanel.repaint();
    }
    

    public void setDestinations(ArrayList<Destination> destinations) {
        choices = new String[destinations.size()];
        int n = 0;
        for(Destination d : destinations){
            choices[n] = d.getDestination();
            n++;
        }
        combobox1 = new JComboBox<String>(choices);
        destPanel1.add(combobox1);
    
        combobox2 = new JComboBox<String>(choices);
        destPanel2.add(combobox2);

        
    }

    public ArrayList<AllButtons> getButtons(){ return buttons; }
    public String getFrom(){ return from.getText(); }
    public String getTo(){ return to.getText(); }
    public String getDate(){ return date.getText(); }
    public String getTime(){ return time.getText(); }
    public String getModel(){ return model.getText(); }
    
}




/* list = new JPanel();
        list.setLayout(new GridLayout(20,1,10,10));
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300,600));
        myList = new ArrayList<>();
        //add(new JScrollPane(list));


       

        */