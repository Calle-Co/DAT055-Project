package start;

public class StartView {
    StartModel m;
    SuperPanel currentPanel;

    public StartView(StartModel m) {
        this.m = m;
        init();
    }

    public void init() {
        StartFrame frame = new StartFrame();
        frame.add(new StartPanel());
    }

    public void nextPanel() {
        
    }
}
