package customer.home;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.*;
import customer.MenuPanel;
import global.AllButtons;
import global.Destination;
import global.WebFetching;
import global.AllButtons.size;

/**
 * Denna klassen visar den grafiska delen av home-delen av programmet.
 * @author Kevin Hao, William Husar
 * @version 2021-03-02
 */
@SuppressWarnings("serial")
public class HomeView extends JPanel {
    private JComboBox<String> combobox1;
    private JComboBox<String> combobox2;
    private WebFetching todaysDat;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private JPanel centerPanel;
    private String[] choices;
    private JComboBox<String> tCombobox;
    private JFormattedTextField dateField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
    
    /**
     * Skapar Home-panelen och all dess innehåll.
     */
    public HomeView() {
        setLayout(null);
        todaysDat = new WebFetching();
        centerPanel = new JPanel();
        centerPanel.setBounds(300, 120, 650, 450);
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.white);
        add(centerPanel);

        // Destination
        String[] choices2 = {"1", "2", "3", "4" };

        JLabel from = new JLabel("From");
        from.setBounds(55, 75, 60, 30);
        from.setFont(new Font("Verdana", 0, 15));
        centerPanel.add(from);

        JLabel to = new JLabel("To");
        to.setBounds(355, 75, 60, 30);
        to.setFont(new Font("Verdana", 0, 15));
        centerPanel.add(to);

        // Datefield
        dateField.setText(todaysDat.getDat());
        dateField.setBounds(50, 220, 80, 35);
        centerPanel.add(dateField);

        JLabel outboundLabel = new JLabel("Outbound");
        outboundLabel.setBounds(55, 190, 100, 20);
        outboundLabel.setFont(new Font("Verdana", 0, 15));
        centerPanel.add(outboundLabel);
        // traveler
        JLabel passengerLabel = new JLabel("Passengers");
        passengerLabel.setBounds(355, 190, 120, 20);
        passengerLabel.setFont(new Font("Verdana", 0, 15));
        centerPanel.add(passengerLabel);

        tCombobox = new JComboBox<String>(choices2);
        tCombobox.setBounds(350, 220, 120, 35);
        centerPanel.add(tCombobox);

        AllButtons searchButton = new AllButtons(size.MEDIUM, "Search");
        searchButton.setBounds(255, 300, 140, 60);
        buttons.add(searchButton);
        centerPanel.add(searchButton);

        // BOOKEDPANEL main = 300, 120, 650, 450
        /*
        JPanel bPanel = new JPanel();
        bPanel.setLayout(null);
        bPanel.setBounds(20, 120, 260, 450);
        bPanel.setBackground(Color.white);
        add(bPanel);

        JLabel bfLabel = new JLabel("Booked Flights");
        bfLabel.setBounds(20, 20, 170, 25);
        bPanel.add(bfLabel);
        bfLabel.setFont(new Font("Verdana", 0, 20));

        JPanel cPanel = new JPanel();
        cPanel.setLayout(null);
        cPanel.setBounds(970, 120, 190, 450);
        cPanel.setBackground(Color.white);
        add(cPanel);

        JLabel cfLabel = new JLabel("Cheap Flights");
        cfLabel.setBounds(20, 20, 170, 25);
        cPanel.add(cfLabel);
        cfLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        */

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        add(menuPanel);
    }

    /**
     * Lägger till alla destinationer i en två olika JCombobox:ar.
     * @param dest destinationer hämtade från databasen
     */
	public void setDestinations(ArrayList<Destination> destinations) {
            choices = new String[destinations.size()];
            int n = 0;
            for(Destination d : destinations){
                choices[n] = d.getDestination();
                n++;
            }
            combobox1 = new JComboBox<String>(choices);
            combobox1.setBounds(50, 110, 250, 50);
            centerPanel.add(combobox1);

            combobox2 = new JComboBox<String>(choices);
            combobox2.setBounds(350, 110, 250, 50);
            centerPanel.add(combobox2);
	}

    /**
     * @param user Användarnamnet på den användare som har loggat in.
     */
    public void setUser(String user){
        JLabel wLabel = new JLabel("Welcome " + user + "!");
        wLabel.setBounds(20, 20, 170, 25);
        centerPanel.add(wLabel);
        wLabel.setFont(new Font("Verdana", 0, 20));
    }

    /**
     * @return En String-Array med alla sökparametrar från startsidan.
     */
	public String[] getSearchParam() {
        String[] param = new String[4];
        param[0] = combobox1.getSelectedItem().toString();
        param[1] = combobox2.getSelectedItem().toString();
        param[2] = dateField.getText();
        param[3] = tCombobox.getSelectedItem().toString();
		return param;
	}

    /**
     * @return En lista med alla knappar som ligger i home-view.
     */
    public ArrayList<AllButtons> getButtons() { return buttons; }
}



