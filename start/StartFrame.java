package start;

import javax.swing.*;
import global.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * @author William Husar, Simon Länsberg
 * @version 2021-02-24
 */
public class StartFrame implements Observable {
    private JFrame startFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;

    public StartFrame() {
        views = new HashMap<>();
        makeFrame();
        init();
    }
    
    public void makeFrame() {
        startFrame = new JFrame();
        startFrame.setPreferredSize(new Dimension(600, 600));
        startFrame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - startFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - startFrame.getHeight()) / 2);
        startFrame.setLocation(x, y);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setTitle("CC Airlines");
    }

    public void init() {
        StartModel sModel = new StartModel();

        WelcomeView wView = new WelcomeView();
        WelcomeController wController = new WelcomeController(sModel, wView);
        wController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s == "Boka!") {
              nextView(views.get("LoginView"));
            }
            if (s == "Admin") {
              nextView(views.get("AdminLoginView"));
            }
        });

        LoginView lView = new LoginView();
        LoginModel lModel = new LoginModel();
        LoginController lController = new LoginController(lModel, lView);
        lController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s == "Login!") {
                if(lController.userLogin(lView.getUsername(), lView.getPassword())) {
                    nextView(new LoadingView());
                    Timer t = new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            notifyObservers("cLogin");
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }
            }
            if (s == "cancel") {
        	    nextView(views.get("WelcomeView"));
            }
            if (s == "Signup!") {
                nextView(views.get("SignupView"));
            }
        });

        AdminLoginView aView = new AdminLoginView();
        AdminLoginModel aModel = new AdminLoginModel();
        AdminLoginController aController = new AdminLoginController(aModel, aView);
        aController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
        if (s == "Login!") {
            if(aController.adminLogin(aView.getPassword())){
                nextView(new LoadingView());
                    Timer t = new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            notifyObservers("aLogin");
                        }
                    });
                    t.setRepeats(false);
                    t.start();
            }   
        }
        if (s == "cancel") {
            nextView(views.get("WelcomeView"));
        }
        });

        SignupView sView = new SignupView();
        SignupModel suModel = new SignupModel();
        SignupController sController = new SignupController(suModel, sView);
        sController.addButtonListener(e -> {
        	String s = ((JButton) e.getSource()).getText();
          	if (s == "Signup!") {
                // Om namnet är tillgängligt kommer man tillbaka till Login.
                // Annars stannar man kvar i Signup  
                if(sController.signUp(sView.getUsername(), sView.getPassword()))
                    nextView(views.get("LoginView"));
            }  
            if (s == "cancel") {
                nextView(views.get("LoginView"));
            }
        });

        views.put("WelcomeView", wView);
        views.put("LoginView", lView);
        views.put("AdminLoginView", aView);
        views.put("SignupView", sView);

        startFrame.add(wView);
        startFrame.pack();
        currentView = wView;
    }

    public void nextView(JPanel view) {
        startFrame.remove(currentView);
        currentView.setVisible(false);
        startFrame.add(view);
        view.setVisible(true);
        startFrame.pack();
        currentView = view;
    }

    public void frameSetVisible(Boolean b) {
        startFrame.setVisible(b);
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