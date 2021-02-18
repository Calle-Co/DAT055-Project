package start;
import java.util.HashMap;
import javax.swing.JPanel;

public class StartController {

    private StartModel model;
    private StartView view;
    
    public StartController(StartModel m, StartView v){
        this.model = m;
        this.view = v;
    }

    public void updateView(String s){
        HashMap<String,JPanel> panels = view.getPanels();
        view.nextPanel(panels.get(s));     
    }

    public void bridge(){
    }
}