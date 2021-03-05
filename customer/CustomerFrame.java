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
import customer.profile.*;


/**
 * Klassen används för att skapa instanser av och initiera
 * alla MVC som tillhör kundens gränssnitt i programmet. Den
 * styr även logiken mellan dessa delar.
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
    private String currentPassword;
    private MyBookingView myBookingView;
    private MyBookingController myBookingController;
    private ProfileView ProfileView;
    
    public CustomerFrame() {
        views = new HashMap<>();
        makeFrame();
    }

    /**
     * Metoden används för att skapa ett fönster där alla
     * vyer kan visas.
     */
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

    /**
     * Metoden används för att initiera alla MVC.
     */
    public void init() {
        initHomeMVC();
        initMyBookingMVC();
        initFlightMVC();
        initBookingMVC();
        initHelpMVC();
        initProfileMVC();
        updateMyBookings();
    }

    /**
     * Metoden används för att initiera det MVC som ansvarar över startsidan.
     */
    public void initHomeMVC() {
        homeView = new HomeView();
        homeView.setUser(currentUser);
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
                nextView(views.get("ProfileView"));
            }
        });
        views.put("HomeView", homeView);
        customerFrame.add(homeView);
        customerFrame.pack();
        currentView = homeView;
    }

    /**
     * Metoden används för att initiera det MVC som ansvarar
     * över sidan som visar kundens bokningar.
     */
    public void initMyBookingMVC() {
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
                nextView(views.get("ProfileView"));
            }
        });
        views.put("Mybookings", myBookingView);
    }

    /**
     * Metoden används för att initiera det MVC som ansvarar
     * över sidan som visar flyg från sökresultatet.
     */
    public void initFlightMVC() {
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
                nextView(views.get("ProfileView"));
            }
        });
        views.put("FlightView", flightView);
    }

    /**
     * Metoden används för att initiera det MVC som ansvarar
     * över sidan som visar stolar som kan bokas i ett flyg.
     */
    public void initBookingMVC() {
        BookingView bookingView = new BookingView();
        BookingModel bookingModel = new BookingModel();
        bookingController = new BookingController(bookingModel, bookingView);
        bookingController.setUser(currentUser);
        bookingController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Book!")){
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
                                    updateMyBookings();
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
                nextView(views.get("ProfileView"));
            }
        });
        views.put("BookingView", bookingView);
    }

    /**
     * Metoden används för att initiera det MVC som ansvarar
     * över sidan som visar hjälpinstruktioner.
     */
    public void initHelpMVC() {
        HelpView helpView = new HelpView();
        HelpModel helpModel = new HelpModel();
        HelpController helpController = new HelpController(helpModel, helpView);
        final JViewport viewport = customer.help.HelpView.helpScrollPane.getViewport();
        
        helpController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("1.1 destination")){
                viewport.setViewPosition(new Point(0, 60));
            }
            else if (s.equals("1.2 flight")){
                viewport.setViewPosition(new Point(0, 650));
            }
            else if (s.equals("1.3 seats")){
                viewport.setViewPosition(new Point(0, 1210));
            }
            else if (s.equals("2.1 check booked flights")){
                viewport.setViewPosition(new Point(0, 2400));
            }
            else if (s.equals("2.2 cancel booked flights")){
                viewport.setViewPosition(new Point(0, 3000));
            }
            else if(s.equals("Logout")){
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
                nextView(views.get("ProfileView"));
            }
        });
        views.put("HelpView", helpView);
    }

    /**
     * Metoden används för att initiera det MVC som ansvarar över profilsidan.
     */
    public void initProfileMVC() {
        ProfileView = new ProfileView();
        ProfileView.setUser(currentUser, currentPassword);
        ProfileModel ProfileModel = new ProfileModel();
        ProfileController ProfileController = new ProfileController(ProfileModel, ProfileView);
        ProfileController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Delete my profile")){
                if(ProfileController.userPopup(currentUser)){
                    notifyObservers("cLogout");
                }
                
            }
            else if(s.equals("Logout")){
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
        });
        views.put("ProfileView", ProfileView);
    }

    /**
     * Metoden används för att söka efter flyg i databasen som passar
     * kundens önskemål med hjälp av tre parametrar.
     * @param from Avgående destination.
     * @param to Ankommande destination.
     * @param date Datum för avgång.
     */
    public void searchFlights(String from, String to, String date) {
        flightController.flightSearch(from, to, date);
        flightController.addFlightButtonListener(e -> {
            FlightInfoButton fib = (FlightInfoButton) e.getSource();
            bookingController.setFlight(fib.getFlightID());
            currentFlight = fib.getFlightID();
            nextView(views.get("BookingView"));
        });
    }

    /**
     * Metoden används för att hämta kundens önskemål och skicka
     * dessa till den metod som söker efter flyg.
     */
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

    /**
     * Metoden används för att byta från nuvarande vy
     * och visa nästa vy.
     * @param view Nästa vy.
     */
    public void nextView(JPanel view) {
        customerFrame.remove(currentView);
        currentView.setVisible(false);
        customerFrame.add(view);
        view.setVisible(true);
        customerFrame.pack();
        currentView = view;
    }

    /**
     * Metoden används för att uppdatera listan med kundens bokade flyg.
     */
    public void updateMyBookings() {
        myBookingController.getBookings(currentUser);
    }

    /**
     * @param user Användarnamn på den som loggat in.
     * @param password Lösenordet på den som loggat in.
     */
    public void setUser(String user, String password) {
        currentUser = user;
        currentPassword = password;
    }

    /**
     * @param b Om fönstret ska synas eller inte.
     */
    public void frameSetVisible(Boolean b) {
        customerFrame.setVisible(b);
    }

    /**
     * Metoden används för att lägga till en observerare.
     * @param observer En observerare.
     */
    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    /**
     * Metoden används för att ta bort nuvarande observerare.
     */
    @Override
    public void removeObserver() {
        this.observer = null;
    }

    /**
     * Metoden används för att skicka ett meddelande till alla observerare.
     * @param message Ett meddelande.
     */
    @Override
    public void notifyObservers(String message) {
        this.observer.update(message);
    }
}