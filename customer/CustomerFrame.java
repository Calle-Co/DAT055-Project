package customer;

import javax.swing.*;
import global.*;
import java.awt.*;
import java.util.HashMap;
import customer.home.*;
import customer.flight.*;
import customer.booking.*;

/**
 * @author William Husar
 * @version 2021-04-24
 */
public class CustomerFrame implements Observable {
    private JFrame customerFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;
    private String currentUser;
    
    public CustomerFrame(String user) {
        this.currentUser = user; 
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
        HomeView hView = new HomeView();
        HomeModel hModel = new HomeModel();
        HomeController hController = new HomeController(hModel, hView);
        hView.setUser(this.currentUser);
        hController.getDestinations();
        hController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if(s.equals("Logout")){
                notifyObservers("cLogout");
            }
            else if(s.equals("Search")){
                System.out.println("BOOOM BOOOOM POOOW");
            }
            
        });

        FlightView fView = new FlightView();
        FlightModel fModel = new FlightModel();
        FlightController fController = new FlightController(fModel, fView);
        fController.flightTest("Gothenburg", "London", "2021-06-20");
        fController.addButtonListener(e -> {
            nextView(views.get("BookingView"));
        });

        BookingView bView = new BookingView();
        BookingModel bModel = new BookingModel();
        BookingController bController = new BookingController(bModel, bView);

        /*views.put("HomeView", hView);
        views.put("FlightView", fView);
        views.put("BookingView", bView);*/
       
        customerFrame.add(hView);
        customerFrame.pack();
        currentView = hView;
        
        
        /*
        customerFrame.add(fView);
        customerFrame.pack();
        currentView = fView;
        */
    }

    public void nextView(JPanel view) {
        customerFrame.remove(currentView);
        currentView.setVisible(false);
        customerFrame.add(view);
        view.setVisible(true);
        customerFrame.pack();
        currentView = view;
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