package customer.profile;

import java.awt.event.*;
import java.util.ArrayList;

import customer.myBookings.MyBookingController;
import global.AllButtons;

/**
 * @author Anna Manfredsson
 * @version 2021-03-05
 */
public class ProfileController {
    private ProfileModel model;
    private ProfileView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public ProfileController(ProfileModel m, ProfileView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    /**
     * Denna metod lägger till en actionListener på alla knappar som hämtades från view.
     * @param al En actionListener
     */
    public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }

    /**
     *Funktion som kallar på clientPopup i view, om man klickar på OK
     * så kallar funktionen på deleteBookings och deleteUser som tar bort användaren och dess bokningar från
     * databasen.
     * @param username Namnet på användaren som någon har klickat på.
     */
    public boolean userPopup(String username){
        if(view.deletePopup()){
            try {
                model.deleteBookings(username);
                model.deleteUser(username);
            } catch (Exception e){
                e.printStackTrace();
                //Måste tas om hand, men borde aldrig kunna inträffa.
            }    
            return true;
        } else{
            return false;
        }
    }

}
