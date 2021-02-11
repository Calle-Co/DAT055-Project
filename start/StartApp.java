package start;

public class StartApp {
    public static void main(String[] args) {
        StartController c = new StartController();
        StartModel m = new StartModel();
        StartView v = new StartView(m);
        v.init();
    }
}
