package customer.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import global.AllButtons;
import global.Destination;
import global.WebFetching;
import global.AllButtons.size;

public class HomeView extends JPanel {

    private JComboBox combobox1;
    private JComboBox combobox2;

    private ImageIcon logga = new ImageIcon("global/resources/smalllogga.PNG");

    public final static int ONE_SECOND = 1000;
    private final SimpleDateFormat clockFormat = new SimpleDateFormat("H:mm:ss");
    private WebFetching todaysDat;
    private ArrayList<Destination> destinations;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private JPanel centerPanel;
    private String[] choices;
    private JComboBox tCombobox;
    private JFormattedTextField dateField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
    
    public HomeView() {
        destinations = new ArrayList<>();
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

        JPanel bPanel = new JPanel();
        bPanel.setLayout(null);
        bPanel.setBounds(20, 120, 260, 450);
        bPanel.setBackground(Color.white);
        add(bPanel);

        JLabel bfLabel = new JLabel("Booked Flights");
        bfLabel.setBounds(20, 20, 170, 25);
        bPanel.add(bfLabel);
        bfLabel.setFont(new Font("Verdana", 0, 20));

        // CHEAPFLIGHTS PANEL main = 300, 120, 650, 450

        JPanel cPanel = new JPanel();
        cPanel.setLayout(null);
        cPanel.setBounds(970, 120, 190, 450);
        cPanel.setBackground(Color.white);
        add(cPanel);

        JLabel cfLabel = new JLabel("Cheap Flights");
        cfLabel.setBounds(20, 20, 170, 25);
        cPanel.add(cfLabel);
        cfLabel.setFont(new Font("Verdana", Font.PLAIN, 20));

        // TOP PANEL
        // centerpanel = 100, 150, 1000, 500

        JPanel topPanel = new JPanel();
        topPanel.setBounds(200, 0, 800, 100);
        topPanel.setBackground(Color.white);
        topPanel.setLayout(null);
        add(topPanel);

        AllButtons homeButton = new AllButtons(size.BORDERLESSWHITE, "Home");
        homeButton.setBounds(50, 20, 160, 60);
        //homeButton.setBackground(Color.white);
        //homeButton.setBorderPainted(false);
        homeButton.setFont(new Font("", Font.BOLD, 25));
        topPanel.add(homeButton);

        AllButtons bkButton = new AllButtons(size.BORDERLESSWHITE, "Bookings");
        bkButton.setBounds(230, 20, 160, 60);
        //bkButton.setBackground(Color.white);
        //bkButton.setBorderPainted(false);
        bkButton.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(bkButton);

        AllButtons hButton = new AllButtons(size.BORDERLESSWHITE, "Help");
        hButton.setBounds(410, 20, 160, 60);
        //hButton.setBackground(Color.white);
        //hButton.setBorderPainted(false);
        hButton.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(hButton);

        AllButtons pButton = new AllButtons(size.BORDERLESSWHITE, "Profile");
        pButton.setBounds(590, 20, 160, 28);
        //pButton.setBackground(Color.white);
        //pButton.setBorderPainted(false);
        pButton.setFont(new Font("Arial", Font.BOLD, 15));
        topPanel.add(pButton);

        AllButtons logoutButton = new AllButtons(size.BORDERLESSWHITE, "Logout");
        buttons.add(logoutButton);
        logoutButton.setBounds(590, 52, 160, 28);
        //logoutButton.setBackground(Color.white);
        //logoutButton.setBorderPainted(false);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        topPanel.add(logoutButton);

        // TIME PANEL (TOPLEFT)
        // top pan = 200, 0, 800, 100

        JPanel timePanel = new JPanel();
            timePanel.setLayout(null);
            timePanel.setBounds(0, 0, 180, 100);
            timePanel.setBackground(Color.white);
            add(timePanel);

            JLabel clockLabel = new JLabel();
            clockLabel.setFont(new Font(clockLabel.getFont().getName(), Font.PLAIN, 25));
            timePanel.add(clockLabel);
            clockLabel.setBounds(40, 45, 120, 40);

            Timer timer = new Timer(ONE_SECOND, new ActionListener(){
                public void actionPerformed(ActionEvent evt) {
                    clockLabel.setText(clockFormat.format(new Date()));
                    clockLabel.repaint();
                }
            });
            clockLabel.setText(clockFormat.format(new Date()));
            timer.start();

            JLabel dateLabel = new JLabel();
            dateLabel.setText(todaysDat.getDat());
            dateLabel.setFont(new Font(dateLabel.getFont().getName(), Font.BOLD, 18));
            dateLabel.setBounds(37, 10, 120, 40);
            timePanel.add(dateLabel);
            // logo (TOPRIGHT)
            // top pan = 200, 0, 800, 100

            JPanel logoPanel = new JPanel();
            logoPanel.setBounds(1000, 0, 180, 100);
            logoPanel.setBackground(Color.white);
            
            JLabel logo = new JLabel(logga);
            logo.setBounds(0, 5, 180, 100);
            logoPanel.add(logo);
            add(logoPanel);



        }

        public ArrayList<AllButtons> getButtons() { return buttons; }

		public void setDestinations(ArrayList<Destination> destinations) {
                this.destinations = destinations;
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

        public void setUser(String user){
            JLabel wLabel = new JLabel("Welcome " + user + "!");
            wLabel.setBounds(20, 20, 170, 25);
            centerPanel.add(wLabel);
            wLabel.setFont(new Font("Verdana", 0, 20));
        }

		public String[] getSearchParam() {
            String[] param = new String[4];
            param[0] = combobox1.getSelectedItem().toString();
            param[1] = combobox2.getSelectedItem().toString();
            param[2] = dateField.getText();
            param[3] = tCombobox.getSelectedItem().toString();
			return param;
		}
    }



