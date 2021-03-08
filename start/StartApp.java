package start;

public class StartApp  {
    private static StartController controller;
    public static void main(String[] args) {
        StartModel model = new StartModel();
        StartView view = new StartView();
        controller = new StartController(model, view);
        view.init(controller);
        
    }

}