package global;

import start.*;
import customer.*;
import admin.*;

/**
 * @author William Husar, Simon Länsberg
 * @version 2021-02-24
 */
public class App implements Observer {
    private static StartFrame startFrame;
    private static Observer app;
    private CustomerFrame customerFrame;
    private AdminFrame adminFrame;

    public App() {
        startFrame = new StartFrame();
        startFrame.frameSetVisible(true);
    }

    public static void main(String[] args) {
        app = new App();
        startFrame.addObserver(app);
    }

    private void customerLogin(String user) {
        customerFrame = new CustomerFrame(user);
        customerFrame.addObserver(app);
        startFrame.frameSetVisible(false);
        customerFrame.frameSetVisible(true);
    }

    private void adminLogin() {
        adminFrame = new AdminFrame();
        adminFrame.addObserver(app);
        startFrame.frameSetVisible(false);
        adminFrame.frameSetVisible(true);
    }

    private void customerLogout() {
        startFrame.frameSetVisible(true);
        customerFrame.frameSetVisible(false);
    }

    private void adminLogout() {
        startFrame.frameSetVisible(true);
        adminFrame.frameSetVisible(false);
    }

    @Override
    public void update(String message) {
        if(message.equals("cLogin")) {
            customerLogin("sven");
        }
        if(message.equals("aLogin")) {
            adminLogin();
        }
        if(message.equals("cLogout")) {
            customerLogout();
        }
        if(message.equals("aLogout")) {
            adminLogout();
        }
    }
}