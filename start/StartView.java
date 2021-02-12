package start;

import javax.swing.JPanel;

public class StartView {
    SuperPanel currentPanel;
    StartFrame frame;

    public StartView() {
        init();
    }

    public void init() {
        frame = new StartFrame();
        frame.setTitle("CC Airlines");
        StartPanel sp = new StartPanel();
        frame.add(sp);
        frame.pack();
        currentPanel = sp;
    }

    public void nextPanel(SuperPanel p) {
        frame.remove(currentPanel);
        frame.add(p);
        frame.pack();
        currentPanel = p;
    }

}
