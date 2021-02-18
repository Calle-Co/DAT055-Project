package global;

import start.*;
import customer.*;
import admin.*;

public class App implements Observer {
    private static StartFrame startFrame;
    private static CustomerFrame customerFrame;
    private static AdminFrame adminFrame;

    public App() {
        startFrame = new StartFrame();
        customerFrame = new CustomerFrame();
        adminFrame = new AdminFrame();
        startFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Observer app = new App();
        startFrame.addObserver(app);
    }

    private void customerLogin() {
        System.out.println("inloggad customer");
        startFrame.setVisible(false);
        // loading...
        customerFrame.setVisible(true);
    }

    private void adminLogin() {
        System.out.println("inloggad admin");
        startFrame.setVisible(false);
        // loading...
        adminFrame.setVisible(true);
    }

    private void logout() {
        //startFrame.setVisible(true);
        //mainFrame.setVisible(false);
    }

    @Override
    public void update(String message) {
        if(message == "cLogin") {
            customerLogin();
        }
        if(message == "aLogin") {
            adminLogin();
        }
    }
}