package customer.flight;

import global.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;


/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class FlightView extends JPanel {
    private List<FlightInfoButton> myList;
    private JPanel list;
    public FlightView(){
        list = new JPanel();
        list.setLayout(new GridLayout(20,1,10,10));
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300,600));
        myList = new ArrayList<>();
        for (int index = 0; index < 20; index++) {
           myList.add(new FlightInfoButton());
        }
        for(FlightInfoButton f : myList){
            list.add(f);
        }
        add(new JScrollPane(list));
    }
    public void addButtonListener(ActionListener al){
        for(FlightInfoButton f : myList){
            f.addActionListener(al);
        }
    }
}
