package global;

import start.*;
import customer.*;
import admin.*;

public class App  {
    private StartFrame startFrame;
    private MainFrame mainFrame; 

    public App(){
        startFrame = new StartFrame();
        mainFrame = new MainFrame();
        startFrame.setVisible(true);
    }
    public static void main(String[] args) {
        new App();
    }

}