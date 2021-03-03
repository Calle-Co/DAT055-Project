package admin.flightsInfo;

import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.event.*;
import admin.AdminMenuPanel;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import global.WebFetching;
import global.*;

public class FlightsInfoView extends JPanel {
    private JPanel destPanel1;
    private JPanel destPanel2;
    private JPanel modelPanel;
    private JPanel addPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private String[] choices;
    private String[] choices1;
    private JComboBox<String> combobox1;
    private JComboBox<String> combobox2;
    private JComboBox<String> combobox3;
    private JLabel from;
    private JLabel to;
    private JLabel model;
    private JFormattedTextField dateField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
    private JFormattedTextField timeField = new JFormattedTextField(new SimpleDateFormat("HH:mm"));
   

    public FlightsInfoView(){

        setLayout(new BorderLayout());
       
    //------------------MENU PANEL--------------------
        AdminMenuPanel menuPanel = new AdminMenuPanel();
        for (AllButtons b : menuPanel.getButtons()){
            buttons.add(b);
        }
        add(menuPanel,BorderLayout.NORTH);

    //---------------ADD FLIGHTS-----------------------
        addPanel = new JPanel();
        
        JLabel addFlight = new JLabel("Add a flight");
        addFlight.setFont(new Font("Verdana", 0, 15));
       
        from = new JLabel("From");
        to = new JLabel("To");
        model = new JLabel("Model");

        dateField.setText(new WebFetching().getDat());
        Date tid = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        timeField.setText(sdf.format(tid));
        
        
        AllButtons addFlightsButton = new AllButtons(AllButtons.size.MEDIUM, "Add Flight");
        buttons.add(addFlightsButton);

        destPanel1 = new JPanel();
        destPanel2 = new JPanel();
        destPanel1.add(from);
        destPanel2.add(to);

        modelPanel = new JPanel();
        modelPanel.add(model);

        JPanel content = new JPanel();

        content.setBorder(new EmptyBorder(20, 100, 150, 100));

        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        CompoundBorder line = new CompoundBorder(empty, blackLine);
        Border grid1Border = BorderFactory.createTitledBorder(line, "Add a flight:");

        addPanel.setLayout(new GridLayout(0, 3, 20, 20));
        addPanel.setBorder(grid1Border);
        addPanel.add(destPanel1);
        addPanel.add(destPanel2);
        addPanel.add(modelPanel);
        addPanel.add(dateField);
        addPanel.add(timeField);
        addPanel.add(addFlightsButton);
        content.add(addPanel);

        add(content, BorderLayout.CENTER);
        setVisible(true);
    }

    public void errorPanel(){
        java.awt.Toolkit.getDefaultToolkit().beep();
        String s = "That flight already exist!\n";
        JOptionPane.showMessageDialog(this, s, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    public void successPanel(){
        String s = "Flight added!\n";
        JOptionPane.showMessageDialog(this, s, "Success!", JOptionPane.INFORMATION_MESSAGE);
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
        destPanel1.setVisible(true);
        combobox2 = new JComboBox<String>(choices);
        destPanel2.add(combobox2);

    }

    public void setPlaneModels(ArrayList<Flight> planeModels) {
        choices1 = new String[planeModels.size()];
        int n = 0;
        for(Flight m : planeModels){
            choices1[n] = m.getModel();
            n++;
        }
        combobox3 = new JComboBox<String>(choices1);
        modelPanel.add(combobox3);
        modelPanel.setVisible(true);
    }
    

    public ArrayList<AllButtons> getButtons(){ return buttons; }
    public String getFrom(){ return combobox1.getSelectedItem().toString(); }
    public String getTo(){ return combobox2.getSelectedItem().toString(); }
    public String getDate(){ return dateField.getText(); }
    public String getTime(){ return timeField.getText(); }
    public String getPlaneModel(){ return combobox3.getSelectedItem().toString(); }
    
}
