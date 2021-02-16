package start;

import java.util.HashMap;
import javax.swing.JPanel;

public class StartView {
    private JPanel currentPanel;
    private StartFrame frame;
    private HashMap<String, JPanel> panels;

    public StartView() {
        panels = new HashMap<>();
    }

    public void init(StartController controller) {
        frame = new StartFrame();
        frame.setTitle("CC Airlines");
        StartPanel sp = new StartPanel(controller);
        LoginPanel lp = new LoginPanel(controller);
        AdminLoginPanel ap = new AdminLoginPanel(controller);
        panels.put("StartPanel", sp);
        panels.put("LoginPanel",lp);
        panels.put("AdminLoginPanel",ap);
        frame.add(sp);
        frame.pack();
        currentPanel = sp;
    }

    public void nextPanel(JPanel p) {
        frame.remove(currentPanel);
        currentPanel.setVisible(false);
        frame.add(p);
        p.setVisible(true);
        frame.pack();
        currentPanel = p;
    }

    public HashMap<String,JPanel> getPanels(){
        return this.panels;
    }

}
