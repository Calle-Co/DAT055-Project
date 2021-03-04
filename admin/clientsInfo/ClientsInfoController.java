package admin.clientsInfo;

import java.util.*;
import global.AllButtons;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * Denna klass är en kontroller för ClientsInfo-panelen, sköter kommunkationen mellan 
 * model och view lägger till lyssnare på alla knappar.
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

    /**
     * Denna metod kallar på ett flertal andra metoder, i korta drag 
     * så hämtar den alla customers från databasen, lägger till dom i en lista
     * och skickar vidare dom till View:n. Den lägger även till en lyssnare på
     * de knappar som representerar användarna. Den kallar även på clear metoder
     * som rensar upp grejer!
     */
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
            e.printStackTrace();
            //Måste tas om hand, men borde aldrig kunna inträffa.
        }
    }
    
    /**
     * Funktion som kallar på clientPopup i view, om man klickar på OK
     * så kallar funktionen på deleteUser som tar bort användaren från
     * databasen.
     * @param username Namnet på användaren som någon har klickat på.
     */
    public void clientPopup(String username){
        if(view.clientPopup(username)){
            try {
                model.deleteUser(username);
            } catch (Exception e){
                e.printStackTrace();
                //Måste tas om hand, men borde aldrig kunna inträffa.
            }    
        }
        listAllUsers();
    }

}
