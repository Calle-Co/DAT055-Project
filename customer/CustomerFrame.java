package customer;

import javax.swing.*;
import global.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @author William Husar
 * @version 2021-04-24
 */
public class CustomerFrame implements Observable {
    private JFrame customerFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;
    
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
        CustomerModel cModel = new CustomerModel();

        HomeView chView = new HomeView();
        HomeController wController = new HomeController(cModel, chView);
        /*wController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s == "Boka!") {
              nextView(views.get("LoginView"));
            }
            if (s == "Admin") {
              nextView(views.get("AdminLoginView"));
            }
        });*/


        views.put("HomeView", chView);

        customerFrame.add(chView);
        customerFrame.pack();
        currentView = chView;
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