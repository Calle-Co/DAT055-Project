package customer;

import java.util.HashMap;
import javax.swing.JPanel;
import global.*;

public class CustomerView {
    private JPanel currentPanel;
    private MainFrame frame;
    private HashMap<String, JPanel> panels;

    public CustomerView() {
        panels = new HashMap<>();
    }

    public void init(CustomerController controller) {
        frame = new MainFrame();
        frame.setTitle("CC Airlines");
        //CustomerPanel sp = new StartPanel(controller);
        //CustomerPanel lp = new LoginPanel(controller);
        //AdminLoginPanel ap = new AdminLoginPanel(controller);
        //SignupPanel sup = new SignupPanel(controller);
        //panels.put("StartPanel", sp);
        //panels.put("LoginPanel",lp);
        //panels.put("AdminLoginPanel",ap);
        //panels.put("SignupPanel",sup);
       // frame.add(sp);
        //frame.pack();
        //currentPanel = sp;
    }

    public void nextPanel(JPanel p) {
        frame.remove(currentPanel);
        currentPanel.setVisible(false);
        frame.add(p);
        p.setVisible(true);
        frame.pack();
        currentPanel = p;
    }

    public HashMap<String,JPanel> getPanels(){
        return this.panels;
    }

}
