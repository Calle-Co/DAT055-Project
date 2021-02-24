package admin;

import javax.swing.*;
import global.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @author William Husar
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
        adminFrame.setTitle("CC Airlines");
    }

    public void init() {

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