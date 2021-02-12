package start;

public class StartView {
    SuperPanel currentPanel;

    public StartView() {
        init();
    }

    public void init() {
        StartFrame frame = new StartFrame();
        frame.add(new StartPanel());
        frame.pack();
    }

    public void nextPanel() {
        
    }
}
