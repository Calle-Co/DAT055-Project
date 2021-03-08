package customer;

import java.util.HashMap;
import javax.swing.JPanel;

public class CustomerController {
    
    private CustomerModel model;
    private CustomerView view;

    public CustomerController(CustomerModel m, CustomerView v){
        this.model = m;
        this.view = v;
    }

    public void updateView(String s){
        HashMap<String,JPanel> panels = view.getPanels();
        view.nextPanel(panels.get(s));     
    }

}

