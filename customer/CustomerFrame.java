package customer;

import javax.swing.*;
import javax.swing.filechooser.FileView;

import global.*;
import java.awt.*;
import java.util.HashMap;
import customer.home.*;
import customer.flight.*;
import customer.booking.*;

/**
 * @author William Husar, Simon Länsberg
 * @version 2021-03-02
 */
public class CustomerFrame implements Observable {
    private JFrame customerFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;
    private HomeView hView;
    private BookingController bController;
    private FlightController fController;
    FlightView fView;
    private String currentUser;
    public CustomerFrame() {
        views = new HashMap<>();
        makeFrame();
        init();
    }

    public void makeFrame() {
        customerFrame = new JFrame();
        customerFrame.setPreferredSize(new Dimension(1200, 800));
        customerFrame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - customerFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - customerFrame.getHeight()) / 2);
        customerFrame.setLocation(x, y);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setTitle("CC Airlines - Customer");
    }

    public void init() {
        hView = new HomeView();
        HomeModel hModel = new HomeModel();
        HomeController hController = new HomeController(hModel, hView);
        hController.getDestinations();
        hController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Search")) {
                bController.reset();
                search();
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                System.out.println("bookings");

            }
            else if(s.equals("Help")){
                System.out.println("help");

            }
            else if(s.equals("Profile")){
                System.out.println("profile");

            }
        });

        fView = new FlightView();
        FlightModel fModel = new FlightModel();
        fController = new FlightController(fModel, fView);
        fController.addMenuButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                System.out.println("bookings");

            }
            else if(s.equals("Help")){
                System.out.println("help");

            }
            else if(s.equals("Profile")){
                System.out.println("profile");

            }
        });

        BookingView bView = new BookingView();
        BookingModel bModel = new BookingModel();
        bController = new BookingController(bModel, bView);
        bController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Boka!")){
                bController.initBooking();
                if(bController.returnToHome()){
                   nextView(new LoadingView());
                   try {
                    bModel.setBooked(bController.getBookingInfo());
                   } catch (Exception e0) {
                       //TODO: handle exception

                       System.out.println("Skiten kaosar yau");
                   } 
                    Thread t3 = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                            if(bView.makeOPane("BookingConfirm")){
                                nextView(views.get("HomeView"));
                            }
                        }   
                    });
                    t3.start();
                }
            }
            else if(s.equals("cancel")){
                bController.reset();
                nextView(views.get("FlightView"));
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                System.out.println("bookings");
            }
            else if(s.equals("Help")){
                System.out.println("help");
            }
            else if(s.equals("Profile")){
                System.out.println("profile");
            }
        });
        
        views.put("HomeView", hView);
        views.put("FlightView", fView);
        views.put("BookingView", bView);
        customerFrame.add(hView);
        customerFrame.pack();
        currentView = hView;
    }

    public void searchFlights(String a, String b, String c){
        fController.flightSearch(a, b, c);
        fController.addFlightButtonListener(e -> {
            FlightInfoButton fib = (FlightInfoButton) e.getSource();
            bController.setFlight(fib.getFlightID());
            nextView(views.get("BookingView"));
        });
    }

    public void search(){
        bController.setLimit(Integer.parseInt(hView.getSearchParam()[3]));
        searchFlights(hView.getSearchParam()[0], hView.getSearchParam()[1], hView.getSearchParam()[2]);
        nextView(new LoadingView());   
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                if(!fController.getNoFlight()){
                    nextView(views.get("FlightView"));
                }
                else{
                    if(fView.makeOPane()){
                        nextView(views.get("HomeView"));
                    }
                }   
            }   
        });
        t2.start();
    }

    public void nextView(JPanel view) {
        customerFrame.remove(currentView);
        currentView.setVisible(false);
        customerFrame.add(view);
        view.setVisible(true);
        customerFrame.pack();
        currentView = view;
    }

    public void setUser(String user) {
        currentUser = user;
        hView.setUser(user);
        bController.setUser(user);
    }

    public void frameSetVisible(Boolean b) {
        customerFrame.setVisible(b);
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