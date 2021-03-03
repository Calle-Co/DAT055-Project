package admin;

import javax.swing.*;
import global.*;
import java.awt.*;
import java.util.HashMap;
import admin.adminHome.*;
import admin.flightsInfo.*;
import admin.destInfo.*;
import admin.clientsInfo.*;

/**
 * @author William Husar & Carl Classon
 * @version 2021-02-24
 */
public class AdminFrame implements Observable {
    private JFrame adminFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;
    
    public AdminFrame() {
        views = new HashMap<>();
        makeFrame();
        init();
    }

    public void makeFrame() {
        adminFrame = new JFrame();
        adminFrame.setPreferredSize(new Dimension(1200, 800));
        adminFrame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - adminFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - adminFrame.getHeight()) / 2);
        adminFrame.setLocation(x, y);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setTitle("CC Airlines - Admin");
    }

    /*===================================HomePage=====================================*/
    public void init() {
        AdminHomeView hView = new AdminHomeView();
        AdminHomeController hController = new AdminHomeController(hView);
        hController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
            if (s.equals("Destinations")) {
                nextView(views.get("DestInfoView"));
            }
            if (s.equals("Clients")) {
        	    nextView(views.get("ClientsInfoView"));
            }
            if (s.equals("Flights")) {
                nextView(views.get("ShowFlightsView"));
            }
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
        });

        /*===================================ClientsInfo=====================================*/
        ClientsInfoView cView = new ClientsInfoView();
        ClientsInfoModel cModel = new ClientsInfoModel();
        ClientsInfoController cController = new ClientsInfoController(cModel,cView);
        cController.listAllUsers();
        cController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s.equals("Update")) {
                cController.listAllUsers();
            }
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
            if (s.equals("Home")) {
                nextView(views.get("AdminHomeView"));
            }
            });
        
        /*===================================DestInfo=====================================*/    
        DestInfoView dView = new DestInfoView();
        DestInfoModel dModel = new DestInfoModel();
        DestInfoController dController = new DestInfoController(dModel, dView);
        dController.listAllDestinations();
        dController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s.equals("Add destination!")) {
                dController.addDestination();
            }
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
            if (s.equals("Home")) {
                nextView(views.get("AdminHomeView"));
            }    
        });

        /*===================================FlightsInfo=====================================*/
        FlightsInfoView fView = new FlightsInfoView();
        FlightsInfoModel fModel = new FlightsInfoModel();
        FlightsInfoController fController = new FlightsInfoController(fModel, fView);
        fController.getDestinations();
        fController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
            if (s.equals("Home")) {
                nextView(views.get("AdminHomeView"));
            }
            /*if (s.equals("Add Flight")) {
                if(fController.addFlight(fView.getFlightnr(), fView.getFrom(), fView.getTo(), fView.getDate(), fView.getTime())){
                //nextView(views.get("LoginView"));  
                }   
            }
             if (s.equals("Update")) {
                //fController.listAllFlights();
            }*/
        });

        
            
        views.put("AdminHomeView", hView);
        views.put("ClientsInfoView", cView);
        views.put("DestInfoView", dView);
        views.put("ShowFlightsView", fView);

        adminFrame.add(hView);
        adminFrame.pack();
        currentView = hView;
    }

    public void nextView(JPanel view) {
        adminFrame.remove(currentView);
        currentView.setVisible(false);
        adminFrame.add(view);
        view.setVisible(true);
        adminFrame.pack();
        currentView = view;
    }

    public void frameSetVisible(Boolean b) {
        adminFrame.setVisible(b);
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver() {
        this.observer = null;
    }

    @Override
    public void notifyObservers(String message) {
        this.observer.update(message);
    }
}