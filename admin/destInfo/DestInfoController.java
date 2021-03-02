package admin.destInfo;

import java.util.*;
import global.AllButtons;
import global.Destination;

import java.awt.event.*;
import javax.swing.JButton;

/**
 * Kontroller för ClientsInfo-panelen.
 * @author Carl Classon
 * @version 2021-02-26
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

    public void listAllDestinations(){
        view.clearDest();
        model.clearDest();
        try {
            view.setUsers(model.fetchDest());
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

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
