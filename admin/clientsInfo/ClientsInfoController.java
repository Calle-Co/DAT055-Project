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
    private ArrayList<AllButtons> clients = new ArrayList<>();

    public ClientsInfoController(ClientsInfoModel m, ClientsInfoView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
        clients = view.getClient();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
    
    public void addClientButtonListener(ActionListener al){
        for(JButton b : clients){
            b.addActionListener(al);
        }
    }

    public void listAllUsers(){
        view.clearUsers();
        model.clearUsers();
        try {
            ArrayList<String> users = model.getUsers();
            view.setUsers(users);
            addClientButtonListener(e -> {
                String s = ((JButton) e.getSource()).getText();
                clientPopup(s);
            }); 
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
    public void clientPopup(String username){
        if(view.clientPopup(username)){
            try {
                model.deleteUser(username);
            } catch (Exception e){
                //TODO: handle exception
            }    
        }
        listAllUsers();
    }

}
