package customer;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class FlightController {
    private FlightView view;


    public FlightController(FlightView view){
        view.addButtonListener(e -> {
            String s = ((FlightInfoButton) e.getSource()).getFlightID();
            {
                System.out.println(s);
            }
        });

    }
}
