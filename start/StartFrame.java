package start;

import start.login.*;
import start.adminLogin.*;
import start.signup.*;
import start.welcome.*;
import javax.swing.*;
import global.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * Denna klass har huvudansvaret för 'start' delen av programmet. Den sköter kommunkationen
 * mellan de olika delarna i start och även kommunikationen med de andra huvuddelarna av programmet.
 * @author William Husar, Simon Länsberg, Carl Classon
 * @version 2021-02-24
 */
public class StartFrame implements Observable {
    private JFrame startFrame;
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;
    private String user;
    private String password;

    /**
     * Metod som initierar makeframe och user och skapar en hashmap med alla vyer.
     */
    public StartFrame() {
        views = new HashMap<>();
        user = "";
        password = "";
        makeFrame();
    }
    
    /**
     * Denna metod skapar den JFrame som alla paneler sen skall ligga i.
     */
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

    /**
     * Denna metod skapar instanser av alla olika MVC:s.
     */
    public void init() {
        initWelcomeMVC();
        initLoginMVC();
        initAdminMVC();
        initSignupMVC();
    }

    /**
     * Denna metod skapar MVC:et som tillhör Welcome paketet.
     */
    public void initWelcomeMVC() {
        WelcomeView wView = new WelcomeView();
        WelcomeController wController = new WelcomeController(wView);
        wController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText();
            if (s.equals("Boka!")){
              nextView(views.get("LoginView"));
            }
            if (s.equals("Admin")) {
              nextView(views.get("AdminLoginView"));
            }
        });
        views.put("WelcomeView", wView);
        startFrame.add(wView);
        startFrame.pack();
        currentView = wView;
    }

    /**
     * Denna metod skapar MVC:et som tillhör Login paketet.
     */
    public void initLoginMVC() {
        LoginView lView = new LoginView();
        LoginModel lModel = new LoginModel();
        LoginController lController = new LoginController(lModel, lView);
        //detta försöker logga in med enter-knappen på tangentbordet
        lController.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tryLogin(lController, lView);
                }    
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        //detta försöker logga in med login-knappen.
        lController.addButtonListener(e -> {
            String s = ((JButton) e.getSource()).getText(); 
            if (s.equals("Login!")) {
                tryLogin(lController, lView);
            }
            if (s.equals("cancel")) {
        	    nextView(views.get("WelcomeView"));
            }
            if (s.equals("Signup!")) {
                nextView(views.get("SignupView"));
            }
        });
        views.put("LoginView", lView);
    }
    
    /**
     * Denna metod skapar MVC:et som tillhör Admin paketet.
     */
    public void initAdminMVC() {
        AdminLoginView aView = new AdminLoginView();
        AdminLoginModel aModel = new AdminLoginModel();
        AdminLoginController aController = new AdminLoginController(aModel, aView);
        aController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
        if (s.equals("Login!")) {
            tryAdminLogin(aController, aView);
        }
        if (s.equals("cancel")){
            nextView(views.get("WelcomeView"));
        }
        });
        aController.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tryAdminLogin(aController, aView);
                }    
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        views.put("AdminLoginView", aView);
    }

    /**
     * Denna metod skapar MVC:et som tillhör Signup paketet.
     */
    public void initSignupMVC() {
        SignupView sView = new SignupView();
        SignupModel suModel = new SignupModel();
        SignupController sController = new SignupController(suModel, sView);
        //Om man trycker på enter på tangentbordet
        sController.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(sController.signUp(sView.getUsername(), sView.getPassword()))
                        nextView(views.get("LoginView"));
                }    
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        //Om man trycker på knappen "signup!"
        sController.addButtonListener(e -> {
        	String s = ((JButton) e.getSource()).getText();
          	if (s.equals("Signup!")) {
                // Om namnet är tillgängligt kommer man tillbaka till Login.
                // Annars stannar man kvar i Signup  
                if(sController.signUp(sView.getUsername(), sView.getPassword()))
                    nextView(views.get("LoginView"));
            }  
            if (s.equals("cancel")) {
                nextView(views.get("LoginView"));
            }
        });
        views.put("SignupView", sView);
    }

    /**
     * Denna metod tar emot den view som skall visas härnäst och 
     * byter ut den nyvarande med denna.
     * @param view vyn som skall visas näst.
     */
    public void nextView(JPanel view) {
        startFrame.remove(currentView);
        currentView.setVisible(false);
        startFrame.add(view);
        view.setVisible(true);
        startFrame.pack();
        currentView = view;
    }

    /**
     * Denna metod försöker logga in en användare. Om det lyckas så startas
     * loadingview och sen loggas man in till customer delen av programmet.
     * @param lController en loginController
     * @param lView en loginView
     */
    public void tryLogin(LoginController lController, LoginView lView){
        if(lController.userLogin(lView.getUsername(), lView.getPassword())) {
            user = lView.getUsername();
            password = lView.getPassword();
            nextView(new LoadingView());       
            Thread t2 = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    notifyObservers("cLogin");
                    nextView(views.get("WelcomeView"));
                }   
            });
            t2.start();
        }
    }

    /**
     * Denna metod försöker logga in en admin. Om det lyckas så startas
     * loadingview och sen loggas man in till admin delen av programmet.
     * @param lController en adminLoginController
     * @param lView en adminLoginView
     */
    public void tryAdminLogin(AdminLoginController aController, AdminLoginView aView){
        if(aController.adminLogin(aView.getPassword())){
            nextView(new LoadingView());
            Thread t2 = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    notifyObservers("aLogin");
                    nextView(views.get("WelcomeView"));
                }          
            });
            t2.start();
        }
    }

    /**
     * Metod som hämtar användarnamnet på den som har loggat in.
     * @return användarnamnet på den som har loggat in.
     */
    public String getUser() {
        return this.user;
    }

    public String getPassword(){
        return this.password;
    }
    
    /**
     * 
     * @param b 
     */
    public void frameSetVisible(Boolean b) {
        startFrame.setVisible(b);
    }

    /**
     * 
     */
    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }
    /**
     * 
     */
    @Override
    public void removeObserver() {
        this.observer = null;
    }

    /**
     * Metod för att uppdatera observern med ett meddelande
     * @param message 
     */
    @Override
    public void notifyObservers(String message) {
        this.observer.update(message);
    }
}