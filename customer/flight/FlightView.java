package customer.flight;

import global.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import customer.MenuPanel;

import java.awt.*;


/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class FlightView extends JPanel {
    private List<FlightInfoButton> myList;
    private JPanel list;
    private ArrayList<JButton> buttons;

    public FlightView() {
        buttons = new ArrayList<>();
        list = new JPanel();
        list.setLayout(new GridLayout(20,1,10,10));
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300,600));
        myList = new ArrayList<>();
        this.add(new JScrollPane(list), BorderLayout.CENTER);
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setPreferredSize(new Dimension(1200,100));
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        this.add(menuPanel, BorderLayout.NORTH);
        this.setVisible(true);
    }

    public boolean makeOPane(){
            JOptionPane.showConfirmDialog(null,
            "There is no such flight! We apologize for the inconvenience",
            "Sorry!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE);
            return true;    
    }
    
    public void addButtonListener(ActionListener al){
        for(FlightInfoButton f : myList){
            f.addActionListener(al);
        }
    }

    public void initButtons(ArrayList<FlightInfoButton> infoButtons) {
        if(!myList.isEmpty()) {
            myList.clear();
            list.removeAll();
        }
        for(FlightInfoButton fib : infoButtons) {
            myList.add(fib);
            list.add(fib);
            buttons.add(fib);
        }
        list.revalidate();
        list.repaint();
    }


    public ArrayList<JButton> getButtons() { return buttons; }
}
