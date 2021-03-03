package customer;

import javax.swing.*;

import global.*;
import java.awt.*;
import java.util.HashMap;
import customer.home.*;
import customer.myBookings.MyBookingController;
import customer.myBookings.MyBookingModel;
import customer.myBookings.MyBookingView;
import customer.flight.*;
import customer.booking.*;
import customer.help.*;

/**
 * @author William Husar, Simon Länsberg
 * @version 2021-03-02
 */
public class CustomerFrame implements Observable {
    private JFrame customerFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;
    private HomeView homeView;
    private BookingController bookingController;
    private FlightController flightController;
    private FlightView flightView;
    private String currentFlight;
    private String currentUser;
    private MyBookingView myBookingView;
    private MyBookingController myBookingController;
    
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
        homeView = new HomeView();
        HomeModel homeModel = new HomeModel();
        HomeController homeController = new HomeController(homeModel, homeView);
        homeController.getDestinations();
        homeController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Search")) {
                bookingController.reset();
                search();
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                nextView(views.get("Mybookings"));
            }
            else if(s.equals("Help")){
                nextView(views.get("HelpView"));
            }
            else if(s.equals("Profile")){
                System.out.println("profile");
            }
        });

        myBookingView = new MyBookingView();
        MyBookingModel myBookingModel = new MyBookingModel();
        myBookingController = new MyBookingController(myBookingModel, myBookingView);
        myBookingController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                nextView(views.get("Mybookings"));
            }
            else if(s.equals("Help")){
                nextView(views.get("HelpView"));
            }
            else if(s.equals("Profile")){
                System.out.println("profile");
            }
        });

        flightView = new FlightView();
        FlightModel flightModel = new FlightModel();
        flightController = new FlightController(flightModel, flightView);
        flightController.addMenuButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                nextView(views.get("Mybookings"));
            }
            else if(s.equals("Help")){
                nextView(views.get("HelpView"));
            }
            else if(s.equals("Profile")){
                System.out.println("profile");
            }
        });

        BookingView bookingView = new BookingView();
        BookingModel bookingModel = new BookingModel();
        bookingController = new BookingController(bookingModel, bookingView);
        bookingController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Boka!")){
                bookingController.initBooking();
                if(bookingController.returnToHome()) {
                    Boolean goHome = true;
                    try {
                        bookingController.setBooked();
                    } catch (Exception e0) {
                        if(bookingView.makeOPane("SeatOccupiedError")) {
                            bookingController.reset();
                            bookingController.setFlight(currentFlight);
                            goHome = false;
                        }  
                    } 
                    if(goHome) {
                        nextView(new LoadingView());
                        Thread t3 = new Thread(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                                if(bookingView.makeOPane("BookingConfirm")){
                                    nextView(views.get("HomeView"));
                                    myBookingController.getBookings(currentUser);
                                }
                            }   
                        });
                        t3.start();
                    }
                }
            }
            else if(s.equals("cancel")){
                bookingController.reset();
                nextView(views.get("FlightView"));
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                nextView(views.get("Mybookings"));
            }
            else if(s.equals("Help")){
                nextView(views.get("HelpView"));
            }
            else if(s.equals("Profile")){
                System.out.println("profile");
            }
        });

        HelpView helpView = new HelpView();
        HelpModel helpModel = new HelpModel();
        HelpController helpController = new HelpController(helpModel, helpView);
        helpController.addMenuButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Home")){
                nextView(views.get("HomeView"));
            }
            else if(s.equals("Bookings")){
                nextView(views.get("Mybookings"));
            }
            else if(s.equals("Help")){
                nextView(views.get("HelpView"));
            }
            else if(s.equals("Profile")){
                System.out.println("profile");
            }
        });

        views.put("HelpView", helpView);
        views.put("HomeView", homeView);
        views.put("FlightView", flightView);
        views.put("BookingView", bookingView);
        views.put("HelpView", helpView);
        views.put("Mybookings", myBookingView);

        customerFrame.add(homeView);
        customerFrame.pack();
        currentView = homeView;
    }

    public void searchFlights(String a, String b, String c) {
        flightController.flightSearch(a, b, c);
        flightController.addFlightButtonListener(e -> {
            FlightInfoButton fib = (FlightInfoButton) e.getSource();
            bookingController.setFlight(fib.getFlightID());
            currentFlight = fib.getFlightID();
            nextView(views.get("BookingView"));
        });
    }

    public void search() {
        bookingController.setLimit(Integer.parseInt(homeView.getSearchParam()[3]));
        searchFlights(homeView.getSearchParam()[0], homeView.getSearchParam()[1], homeView.getSearchParam()[2]);
        nextView(new LoadingView());   
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                if(!flightController.getNoFlight()) {
                    nextView(views.get("FlightView"));
                }
                else{
                    if(flightView.makeOPane()) {
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
        myBookingController.getBookings(user);
        homeView.setUser(user);
        bookingController.setUser(user);
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