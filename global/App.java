package global;

import start.*;
import customer.*;
import admin.*;

/**
 * Programmets huvudkomponent.
 * Klassen ser till att starta programmet och skapa
 * instanser av de tre komponenterna som ansvarar
 * för generell programstruktur.
 * @author William Husar, Simon Länsberg
 * @version 2021-03-04
 */
public class App implements Observer {
    private static StartFrame startFrame;
    private static Observer app;
    private CustomerFrame customerFrame;
    private AdminFrame adminFrame;

    public App() {
        startFrame = new StartFrame();
        startFrame.init();
        startFrame.frameSetVisible(true);
    }

    /**
     * Metoden startar programmet.
     * @param args Eventuella argument.
     */
    public static void main(String[] args) {
        app = new App();
        startFrame.addObserver(app);
    }

    /**
     * Metoden skapar en instans av och initierar
     * komponenten som ansvarar för kundens gränssnitt 
     * efter lyckad inloggning.
     */
    private void customerLogin() {
        customerFrame = new CustomerFrame();
        customerFrame.setUser(startFrame.getUser(), startFrame.getPassword());
        customerFrame.init();
        customerFrame.addObserver(app);
        startFrame.frameSetVisible(false);
        customerFrame.frameSetVisible(true);
    }

    /**
     * Metoden skapar en instans av och initierar
     * komponenten som ansvarar för administratörens
     * gränssnitt efter lyckad inloggning.
     */
    private void adminLogin() {
        adminFrame = new AdminFrame();
        adminFrame.init();
        adminFrame.addObserver(app);
        startFrame.frameSetVisible(false);
        adminFrame.frameSetVisible(true);
    }

    /**
     * Metoden ser till att stänga kundens
     * gränssnitt och återgå till startsidan.
     */
    private void customerLogout() {
        startFrame.frameSetVisible(true);
        customerFrame.frameSetVisible(false);
    }

    /**
     * Metoden ser till att stänga administratörens
     * gränssnitt och återgå till startsidan.
     */
    private void adminLogout() {
        startFrame.frameSetVisible(true);
        adminFrame.frameSetVisible(false);
    }

    /**
     * Metoden får en uppdatering från en
     * observerad klass och kallar på någon
     * relevant metod med hänsyn till meddelandet.
     * @param message Ett meddelande från en observerad klass.
     */
    @Override
    public void update(String message) {
        if(message.equals("cLogin")) {
            customerLogin();
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