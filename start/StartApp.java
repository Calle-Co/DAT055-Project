package start;

public class StartApp {
    public static void main(String[] args) {
        StartModel model = new StartModel();
        StartView view = new StartView();
        StartController controller = new StartController(model,view);
        view.init(controller);

    }
}
