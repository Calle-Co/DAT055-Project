package start;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class StartFrame extends JFrame {
  private JPanel currentView;
  private HashMap<String, JPanel> views;

  public StartFrame() {
    setPreferredSize(new Dimension(600,600));
    pack();
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x,y);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("CC Airlines");
    views = new HashMap<>();
    init();
  }

  public void init() {
    StartModel sModel = new StartModel();
    
    WelcomeView wView = new WelcomeView();
    WelcomeController wController = new WelcomeController(sModel, wView);
    wController.addButtonListener(e -> {
      String s = ((JButton)e.getSource()).getText();
      if(s == "Boka!") {
        nextView(views.get("LoginView"));
      }
      if(s == "Admin") {
        nextView(views.get("AdminLoginView"));
      }
    });

    LoginView lView = new LoginView();
    LoginController cController = new LoginController(sModel, lView);

    AdminLoginView aView = new AdminLoginView();
    AdminLoginController aController = new AdminLoginController(sModel, aView);

    SignupView sView = new SignupView();
    SignupController sController= new SignupController(sModel, sView);

    views.put("WelcomeView", wView);
    views.put("LoginView",lView);
    views.put("AdminLoginView",aView);
    views.put("SignupView",sView);

    add(wView);
    pack();
    currentView = wView;
  }

  public void nextView(JPanel view) {
    remove(currentView);
    currentView.setVisible(false);
    add(view);
    view.setVisible(true);
    pack();
    currentView = view;
  }
}
