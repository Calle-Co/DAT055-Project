package customer.help;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import customer.MenuPanel;
import global.AllButtons;
import global.AllButtons.size;

/**
 * Denna klassen visar den grafiska delen av help-delen av programmet.
 * @author Kevin Hao
 * @version 2021-03-03
 */
@SuppressWarnings("serial")
public class HelpView extends JPanel {
    private ArrayList<JButton> buttons;
    public static JScrollPane helpScrollPane;
    //private ImageIcon dest1helpIcon = new ImageIcon("src/global/resources/dest1help.PNG");
    private ImageIcon dest1helpIcon = new ImageIcon(getClass().getClassLoader().getResource("dest1help.PNG"));
    //private ImageIcon dest2helpIcon = new ImageIcon("src/global/resources/dest2help.PNG");
    private ImageIcon dest2helpIcon = new ImageIcon(getClass().getClassLoader().getResource("dest2help.PNG"));
    //private ImageIcon dest3helpIcon = new ImageIcon("src/global/resources/dest3help.PNG");
    private ImageIcon dest3helpIcon = new ImageIcon(getClass().getClassLoader().getResource("dest3help.PNG"));
    //private ImageIcon dest31helpIcon = new ImageIcon("src/global/resources/dest31help.PNG");
    private ImageIcon dest31helpIcon = new ImageIcon(getClass().getClassLoader().getResource("dest31help.PNG"));
    //private ImageIcon booked1helpIcon = new ImageIcon("src/global/resources/booked1help.PNG");
    private ImageIcon booked1helpIcon = new ImageIcon(getClass().getClassLoader().getResource("booked1help.PNG"));
    //private ImageIcon booked12helpIcon = new ImageIcon("src/global/resources/booked12help.PNG");
    private ImageIcon booked12helpIcon = new ImageIcon(getClass().getClassLoader().getResource("booked12help.PNG"));
    private JLabel oneLabel;

    /**
     * Skapar Home-panelen och all dess innehåll.
     */
    public HelpView(){
        setLayout(null);

        buttons = new ArrayList<>();

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        add(menuPanel);

        JPanel helpmenuPanel = new JPanel();
        helpmenuPanel.setLayout(null);
        helpmenuPanel.setBounds(20, 120, 300, 500);
        helpmenuPanel.setBackground(Color.white);
        add(helpmenuPanel);

        JLabel manualLabel = new JLabel("Manual");
        manualLabel.setBounds(20, 20, 150, 35);
        manualLabel.setFont(new Font("Verdana", 1, 20));
        helpmenuPanel.add(manualLabel);

        JLabel desthelpLabel = new JLabel("1. Book flight");
        desthelpLabel.setBounds(20, 90, 260, 20);
        desthelpLabel.setFont(new Font("Arial", 1, 18));
        helpmenuPanel.add(desthelpLabel);
        
        AllButtons dest1helpButton = new AllButtons(size.BORDERLESSWHITE, "1.1 destination");
        dest1helpButton.setBounds(20, 115, 260, 30);
        dest1helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        dest1helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(dest1helpButton);
        buttons.add(dest1helpButton);
        
        AllButtons dest2helpButton = new AllButtons(size.BORDERLESSWHITE, "1.2 flight");
        dest2helpButton.setBounds(20, 150, 260, 30);
        dest2helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        dest2helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(dest2helpButton);
        buttons.add(dest2helpButton);

        AllButtons dest3helpButton = new AllButtons(size.BORDERLESSWHITE, "1.3 seats");
        dest3helpButton.setBounds(20, 185, 260, 30);
        dest3helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        dest3helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(dest3helpButton);
        buttons.add(dest3helpButton);
        
        
        JLabel bookinghelpLabel = new JLabel("2. Bookings");
        bookinghelpLabel.setBounds(20, 230, 260, 20);
        bookinghelpLabel.setFont(new Font("Arial", 1, 18));
        helpmenuPanel.add(bookinghelpLabel);

        AllButtons booked1helpButton = new AllButtons(size.BORDERLESSWHITE, "2.1 check booked flights");
        booked1helpButton.setBounds(20, 255, 260, 30);
        booked1helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        booked1helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(booked1helpButton);
        buttons.add(booked1helpButton);

        AllButtons booked2helpButton = new AllButtons(size.BORDERLESSWHITE, "2.2 cancel booked flights");
        booked2helpButton.setBounds(20, 290, 260, 30);
        booked2helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        booked2helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(booked2helpButton);
        buttons.add(booked2helpButton);

        JLabel profilehelpLabel = new JLabel("3. Profile");
        profilehelpLabel.setBounds(20, 335, 260, 20);
        profilehelpLabel.setFont(new Font("Arial", 1, 18));
        helpmenuPanel.add(profilehelpLabel);

        // COMPONENT PANEL

        //1.0 + 1.1
        JPanel componentPanel = new JPanel();
        componentPanel.setBackground(Color.WHITE);
        componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.Y_AXIS));
        componentPanel.add(new EmptyLabel(2));

        oneLabel = new JLabel("1. Book flight");
        oneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        oneLabel.setFont(new Font("Arial", 1, 20));
        oneLabel.setMaximumSize(new Dimension(700, 100));
        componentPanel.add(oneLabel);
        componentPanel.add(new EmptyLabel(2));

        JLabel oneponeLabel = new JLabel("1.1 destination");
        oneponeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        oneponeLabel.setFont(new Font("Arial", 1, 17));
        oneponeLabel.setMaximumSize(new Dimension(600, 100));
        componentPanel.add(oneponeLabel);
        componentPanel.add(new EmptyLabel(2));
        
        JLabel dest1picLabel = new JLabel(dest1helpIcon);
        dest1picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(dest1picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taOnepone = new JTextArea("To book your flight, start by choosing the city you wish to depart from as well as your destination by clicking on the dropdown menus under 'From' and 'to'. After that, choose the date along with the amount of passengers you wish to book the tickets for and click the Search button");
        taOnepone.setAlignmentX(Component.CENTER_ALIGNMENT);
        taOnepone.setMaximumSize(new Dimension(600, 400));
        taOnepone.setFont(new Font("Arial", 0, 16));
        taOnepone.setLineWrap(true);
        taOnepone.setWrapStyleWord(true);
        taOnepone.setEditable(false);
        taOnepone.setBackground(Color.white);
        componentPanel.add(taOnepone);
        componentPanel.add(new EmptyLabel(2));

        //1.2
        JLabel oneptwoLabel = new JLabel("1.2 flight");
        oneptwoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        oneptwoLabel.setFont(new Font("Arial", 1, 17));
        oneptwoLabel.setMaximumSize(new Dimension(600, 100));
        componentPanel.add(oneptwoLabel);
        componentPanel.add(new EmptyLabel(2));

        JLabel dest2picLabel = new JLabel(dest2helpIcon);
        dest2picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(dest2picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taOneptwo = new JTextArea("Multiple results with various available dates and time will show up on your page. Choose the flight that suits you the best by clicking on the flight button.");
        taOneptwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        taOneptwo.setMaximumSize(new Dimension(600, 400));
        taOneptwo.setFont(new Font("Arial", 0, 17));
        taOneptwo.setLineWrap(true);
        taOneptwo.setWrapStyleWord(true);
        taOneptwo.setEditable(false);
        taOneptwo.setBackground(Color.white);
        componentPanel.add(taOneptwo);
        componentPanel.add(new EmptyLabel(2));

        //1.3
        JLabel onepthreeLabel = new JLabel("1.3 seats");
        onepthreeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        onepthreeLabel.setFont(new Font("Arial", 1, 17));
        onepthreeLabel.setMaximumSize(new Dimension(600, 100));
        componentPanel.add(onepthreeLabel);
        componentPanel.add(new EmptyLabel(2));

        JLabel dest3picLabel = new JLabel(dest3helpIcon);
        dest3picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(dest3picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taOnepthree = new JTextArea("After that, choose the vacant seats that you wish to book for by clicking on the seat icons. The colors green, blue and red signifies whether the seat is vacant, chosen by you or already booked by another customer. To book the seat, enter the name and the age of the passenger and finish by clicking on the 'Boka!' button.");
        taOnepthree.setAlignmentX(Component.CENTER_ALIGNMENT);
        taOnepthree.setMaximumSize(new Dimension(600, 400));
        taOnepthree.setFont(new Font("Arial", 0, 17));
        taOnepthree.setLineWrap(true);
        taOnepthree.setWrapStyleWord(true);
        taOnepthree.setEditable(false);
        taOnepthree.setBackground(Color.white);
        componentPanel.add(taOnepthree);
        componentPanel.add(new EmptyLabel(2));

        JLabel dest31picLabel = new JLabel(dest31helpIcon);
        dest31picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(dest31picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taOnepthreeone = new JTextArea("When you have successfully booked your flight, a popup window will appear, confirming your booking(s).");
        taOnepthreeone.setAlignmentX(Component.CENTER_ALIGNMENT);
        taOnepthreeone.setMaximumSize(new Dimension(600, 400));
        taOnepthreeone.setFont(new Font("Arial", 0, 17));
        taOnepthreeone.setLineWrap(true);
        taOnepthreeone.setWrapStyleWord(true);
        taOnepthreeone.setEditable(false);
        taOnepthreeone.setBackground(Color.white);
        componentPanel.add(taOnepthreeone);
        componentPanel.add(new EmptyLabel(2));

        //2.0 + 2.1
        JLabel twoLabel = new JLabel("2. Bookings");
        twoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        twoLabel.setFont(new Font("Arial", 1, 20));
        twoLabel.setMaximumSize(new Dimension(700, 100));
        componentPanel.add(twoLabel);
        componentPanel.add(new EmptyLabel(2));

        JLabel twoponeLabel = new JLabel("2.1 checking booked flights");
        twoponeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        twoponeLabel.setFont(new Font("Arial", 1, 17));
        twoponeLabel.setMaximumSize(new Dimension(600, 100));
        componentPanel.add(twoponeLabel);
        componentPanel.add(new EmptyLabel(2));

        JLabel booked1picLabel = new JLabel(booked1helpIcon);
        booked1picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(booked1picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taTwopone = new JTextArea("If you have bookings at CC Airlines, you can check your booked flights by clicking on the Bookings button at the top of the page. By doing so, you will be directed the bookings page where you can check the date and time of you next flight(s).");
        taTwopone.setAlignmentX(Component.CENTER_ALIGNMENT);
        taTwopone.setMaximumSize(new Dimension(600, 400));
        taTwopone.setFont(new Font("Arial", 0, 17));
        taTwopone.setLineWrap(true);
        taTwopone.setWrapStyleWord(true);
        taTwopone.setEditable(false);
        taTwopone.setBackground(Color.white);
        componentPanel.add(taTwopone);
        componentPanel.add(new EmptyLabel(2));

        //2.2
        JLabel twoptwoLabel = new JLabel("2.2 cancel booked flights");
        twoptwoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        twoptwoLabel.setFont(new Font("Arial", 1, 17));
        twoptwoLabel.setMaximumSize(new Dimension(600, 100));
        componentPanel.add(twoptwoLabel);
        componentPanel.add(new EmptyLabel(2));

        JLabel booked12picLabel = new JLabel(booked12helpIcon);
        booked12picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(booked12picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taTwoptwo = new JTextArea("If you would wish to cancel your already booked flight, you are able to do so by clicking on the flight button, where a popup window will appear for you to confirm the cancellation of your ticket.");
        taTwoptwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        taTwoptwo.setMaximumSize(new Dimension(600, 400));
        taTwoptwo.setFont(new Font("Arial", 0, 17));
        taTwoptwo.setLineWrap(true);
        taTwoptwo.setWrapStyleWord(true);
        taTwoptwo.setEditable(false);
        taTwoptwo.setBackground(Color.white);
        componentPanel.add(taTwoptwo);
        componentPanel.add(new EmptyLabel(2));

        //change formatshere

        /*JLabel destpicLabel = new JLabel(dest3helpIcon);
        dest3picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        componentPanel.add(dest3picLabel);
        componentPanel.add(new EmptyLabel(2));

        JTextArea taOnepthree = new JTextArea(5, 5);
        taOnepthree.setAlignmentX(Component.CENTER_ALIGNMENT);
        taOnepthree.setMaximumSize(new Dimension(600, 400));
        taOnepthree.setLineWrap(true);
        taOnepthree.setEditable(false);
        taOnepthree.setBackground(Color.black);
        componentPanel.add(taOnepthree);
        componentPanel.add(new EmptyLabel(2));*/
        
        helpScrollPane = new JScrollPane(componentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        helpScrollPane.setBounds(340, 120, 830, 500);
        helpScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(helpScrollPane);

        //add(instructionPanel);
        //final JViewport viewport = helpScrollPane.getViewport();
    }

    /**
     * @return En lista med alla knappar som ligger i Help-view.
     */
    public ArrayList<JButton> getButtons() { return buttons; }
}