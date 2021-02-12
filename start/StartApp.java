package start;

public class StartApp {
    public static void main(String[] args) {
        StartModel m = new StartModel();
        StartView v = new StartView();
        StartController c = new StartController(m,v);
    }
}
