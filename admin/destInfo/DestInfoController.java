package admin.destInfo;

import java.util.*;
import global.AllButtons;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * Kontroller för DestInfo-panelen. Fungerar som en mellanhand mellan
 * DestInfoModel och DestInfoView. Den tar information från den ena och skickar 
 * vidare till den andra.
 * @author Carl Classon
 * @version 2021-03-02
 */
public class DestInfoController {
    private DestInfoModel model;
    private DestInfoView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public DestInfoController(DestInfoModel m, DestInfoView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    /**
     * Denna metod fungerar som en mellanhand mellan modellen och vyn.
     * Den ser till att model-klassen hämtar hem destinationerna från databasen
     * och så skickar den en lista av alla destinationerna till setUsers() i vyn.
     * Den rensar även upp mellan anropen så att det inte blir duplicerade destinationer.
     */
    public void listAllDestinations(){
        view.clearDest();
        model.clearDest();
        try {
            view.setUsers(model.fetchDest());
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /**
     * Denna metod fungerar som en mellanhand mellan modellen och vyn.
     * Den ser till att view visar en popup ruta där användaren kan skriva in
     * information. Om användaren klickar på OK så skickas de inskrivna värdena
     * vidare till model-klassen.
     */
    public void addDestination(){
        if(view.destPopup()){
            try{
                model.addDestination(view.getDest(), view.getAbbrev());
            } catch (Exception e){
                //TODO handle exception
            }
        }
        listAllDestinations();
    }
}
