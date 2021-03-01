package admin.clientsInfo;

import java.util.*;
import global.AllButtons;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * Kontroller för ClientsInfo-panelen.
 * @author Carl Classon
 * @version 2021-02-26
 */
public class ClientsInfoController {
    private ClientsInfoModel model;
    private ClientsInfoView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public ClientsInfoController(ClientsInfoModel m, ClientsInfoView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    public void listAllUsers(){
        view.clearUsers();
        model.clearUsers();
        try {
            ArrayList<String> users = model.getUsers();
            view.setUsers(users);
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    

}
