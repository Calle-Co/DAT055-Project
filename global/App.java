package global;

import start.*;
import customer.*;
import admin.*;
import java.util.concurrent.TimeUnit;

/**
 * @author William Husar, Simon Länsberg
 * @version 2021-02-24
 */
public class App implements Observer {
    private static StartFrame startFrame;
    private static CustomerFrame customerFrame;
    private static AdminFrame adminFrame;

    public App() {
        startFrame = new StartFrame();
        customerFrame = new CustomerFrame();
        adminFrame = new AdminFrame();
        startFrame.frameSetVisible(true);
    }

    public static void main(String[] args) {
        Observer app = new App();
        startFrame.addObserver(app);
        customerFrame.addObserver(app);
        adminFrame.addObserver(app);
    }

    private void customerLogin() {
        System.out.println("inloggad customer");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        startFrame.frameSetVisible(false);
        customerFrame.frameSetVisible(true);
    }

    private void adminLogin() {
        System.out.println("inloggad admin");
        startFrame.frameSetVisible(false);
        // loading...
        adminFrame.frameSetVisible(true);
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