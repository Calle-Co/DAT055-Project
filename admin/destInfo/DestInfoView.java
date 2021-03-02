package admin.destInfo;

import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import global.AllButtons;
import global.Destination;
import global.AllButtons.*;
import admin.AdminMenuPanel;

@SuppressWarnings("serial")
public class DestInfoView extends JPanel{
    private JPanel destPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private String destination;
    private String abbreviation;

    public DestInfoView(){
        destPanel = new JPanel();
        destPanel.setLayout(new GridLayout(20,1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        AdminMenuPanel menuPanel = new AdminMenuPanel();
        for (AllButtons b : menuPanel.getButtons()){
            buttons.add(b);
        }
        
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout());

        AllButtons updateButton = new AllButtons(size.LARGE, "Add destination!");
        buttons.add(updateButton);
        updatePanel.add(updateButton,BorderLayout.CENTER);
        
        topPanel.add(updatePanel,BorderLayout.SOUTH);
        topPanel.add(menuPanel,BorderLayout.NORTH);

        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(new JScrollPane(destPanel));
        setVisible(true);
    }

    /**
     * Lägger till alla destinationer i en panel
     * @param dest destinationer hämtade från databasen
     */
    public void setUsers(ArrayList<Destination> dest){
        for(Destination name : dest){
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new GridLayout(1,1));
           
            Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            Border blackLine = BorderFactory.createLineBorder(Color.black);
            CompoundBorder line = new CompoundBorder(empty, blackLine);
            
            JLabel destLabel = new JLabel(name.getDestination() + " " + "[" + name.getAbv() + "]");
            destLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        
            namePanel.add(destLabel);
            namePanel.setBorder(line);
            destPanel.add(namePanel);
        }
        Border empty = BorderFactory.createEmptyBorder(0, 300, 0, 300);
        destPanel.setBorder(empty);
        destPanel.revalidate();
        destPanel.repaint();
    }

    public void clearDest(){
        destPanel.removeAll();
        destPanel.revalidate();
        destPanel.repaint();
    }

    public boolean destPopup(){
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Destination:"));
        panel.add(field1);
        panel.add(new JLabel("Abbreviation:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add destination",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            this.destination = field1.getText();
            this.abbreviation = field2.getText(); 
            return true;
        } else {
            return false;
        }
    }
    
    public String getDest() { return destination; }
    public String getAbbrev() { return abbreviation; }
    public ArrayList<AllButtons> getButtons() { return buttons; }
}
