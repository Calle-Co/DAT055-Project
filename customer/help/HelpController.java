package customer.help;

import java.util.ArrayList;
import java.awt.event.*;

import global.AllButtons;

public class HelpController {
    private HelpModel model;
    private HelpView view;

    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public HelpController(HelpModel m, HelpView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }
}
