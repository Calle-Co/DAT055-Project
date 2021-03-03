package customer.myBookings;

import java.util.*;
import global.AllButtons;
import java.awt.event.*;
import javax.swing.JButton;

public class MyBookingController {
    private MyBookingModel model;
    private MyBookingView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();


    public MyBookingController(MyBookingModel m, MyBookingView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public int getBookings(String user){
        try {
            view.initButtons(model.getFlight(model.getBookings(user)));
        } catch (Exception e) {
            //TODO: handle exception
            return -1;
        }
        return 0;
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}
