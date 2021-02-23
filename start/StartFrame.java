package start;

import javax.swing.*;

import global.LoadingView;
import global.Observable;
import global.Observer;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("serial")
public class StartFrame extends JFrame implements Observable {
    private JPanel currentView;
    private HashMap<String, JPanel> views;
    private Observer observer;

  public StartFrame() {
      setPreferredSize(new Dimension(600, 600));
      pack();
      Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (int) ((dimension.getWidth() - getWidth()) / 2);
      int y = (int) ((dimension.getHeight() - getHeight()) / 2);
      setLocation(x, y);
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
        String s = ((JButton) e.getSource()).getText();
        if (s == "Boka!") {
          nextView(views.get("LoginView"));
        }
        if (s == "Admin") {
          nextView(views.get("AdminLoginView"));
        }
      });

      LoginView lView = new LoginView();
      LoginController lController = new LoginController(sModel, lView);
      lController.addButtonListener(e -> {
        String s = ((JButton) e.getSource()).getText();
        if (s == "Login!") {
		      notifyObservers("cLogin");
        }
        if (s == "cancel") {
    	    nextView(views.get("WelcomeView"));
        }
        if (s == "Signup!") {
          nextView(views.get("SignupView"));
        }
      });

      AdminLoginView aView = new AdminLoginView();
      AdminLoginController aController = new AdminLoginController(sModel, aView);
      aController.addButtonListener(e -> {
      String s = ((JButton) e.getSource()).getText();
      if (s == "Login!") {
          notifyObservers("aLogin");
      }
      if (s == "cancel") {
        nextView(views.get("WelcomeView"));
      }
      });

      SignupView sView = new SignupView();
      SignupController sController = new SignupController(sModel, sView);
      sController.addButtonListener(e -> {
      String s = ((JButton) e.getSource()).getText();
      if (s == "Signup!") {
        try {
			// This place every new user in a file, with username and password
			// seperated by a blankspace, and each user on a new line
        	BufferedWriter bw = new BufferedWriter(new FileWriter("start/userInfo/userFile.txt",true));
          	bw.append(sView.getUsername());
			  bw.append(' ');  
		 	  bw.append(sView.getPassword());  
			  bw.append("\n");
      	bw.close();
			  
        } catch (IOException ex){
          	ex.printStackTrace();
        }  
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
