package start.adminLogin;

import javax.swing.JButton;
import global.AllButtons;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminLoginController {

    private AdminLoginModel model;
    private AdminLoginView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public AdminLoginController(AdminLoginModel m, AdminLoginView v) {
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    public boolean adminLogin(String password) {
        try{
            if(model.adminLogin(password)) {
                return true;
            } else {
                view.errorPanel();
                return false;
            }

        } catch (SQLException e) {    
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
