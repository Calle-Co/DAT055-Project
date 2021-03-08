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
 * Denna klass har huvudansvaret för 'admin' delen av programmet. Den sköter kommunkationen
 * mellan de olika delarna i adminpaketet och även kommunikationen med de andra huvuddelarna av programmet.
 * @author William Husar, Carl Classon
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
    }

    /**
     * Denna metod skapar den JFrame som alla paneler sen skall ligga i.
     */
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

    /**
     * Denna metod skapar instanser av alla olika MVC:s.
     */
    public void init() {
        initAdminHomeMVC();
        initClientsInfoMVC();   
        initDestInfoMVC();
        initFlightsInfoMVC();
    }

    /**
     * Denna metod skapar MVC:et som tillhör adminHome paketet.
     */
    public void initAdminHomeMVC() {
        AdminHomeView homeView = new AdminHomeView();
        AdminHomeController homeController = new AdminHomeController(homeView);
        homeController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
            if (s.equals("Destinations")) {
                nextView(views.get("DestInfoView"));
            }
            if (s.equals("Clients")) {
        	    nextView(views.get("ClientsInfoView"));
            }
            if (s.equals("Flights")) {
                nextView(views.get("FlightsInfoView"));
            }
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
        });
        views.put("AdminHomeView", homeView);
        adminFrame.add(homeView);
        adminFrame.pack();
        currentView = homeView;
    }

    /**
     * Denna metod skapar MVC:et som tillhör  paketet.
     */
    public void initClientsInfoMVC() {
        ClientsInfoView clientsView = new ClientsInfoView();
        ClientsInfoModel clientsModel = new ClientsInfoModel();
        ClientsInfoController clientsController = new ClientsInfoController(clientsModel,clientsView);
        clientsController.listAllUsers();
        clientsController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s.equals("Update")) {
                clientsController.listAllUsers();
            }
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
            if (s.equals("Home")) {
                nextView(views.get("AdminHomeView"));
            }
        });
        views.put("ClientsInfoView", clientsView);
    }
    
    /**
     * Denna metod skapar MVC:et som tillhör destInfo paketet.
     */
    public void initDestInfoMVC() {
        DestInfoView destView = new DestInfoView();
        DestInfoModel destModel = new DestInfoModel();
        DestInfoController destController = new DestInfoController(destModel, destView);
        destController.listAllDestinations();
        destController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s.equals("Add destination!")) {
                destController.addDestination();
            }
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
            if (s.equals("Home")) {
                nextView(views.get("AdminHomeView"));
            }    
        });
        views.put("DestInfoView", destView);
    }

    /**
     * Denna metod skapar MVC:et som tillhör  paketet.
     */
    public void initFlightsInfoMVC() {
        FlightsInfoView flightsView = new FlightsInfoView();
        FlightsInfoModel flightsModel = new FlightsInfoModel();
        FlightsInfoController flightsController = new FlightsInfoController(flightsModel, flightsView);
        flightsController.getDestinations();
        flightsController.getModels();
        flightsController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
            if (s.equals("Logout")) {
                notifyObservers("aLogout");
            }
            if (s.equals("Home")) {
                nextView(views.get("AdminHomeView"));
            }
            if (s.equals("Add Flight")) {
                flightsController.addFlight();
            }
        });
        views.put("FlightsInfoView", flightsView);
    }

    /**
    * Denna metod tar emot den view som skall visas härnäst och 
    * byter ut den nyvarande med denna.
    * @param view vyn som skall visas näst.
    */
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