package admin;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import global.AllButtons;
import global.WebFetching;
import global.AllButtons.size;

/**
 * @author Anna Manfredsson
 * @version 2021-03-04
 */
@SuppressWarnings("serial")
public class AdminMenuPanel extends JPanel{
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
    private final SimpleDateFormat clockFormat = new SimpleDateFormat("H:mm:ss");
    private WebFetching todaysDat;
    
    /**
     * Denna metod skapar en menypanel med datum, hemknapp, logga-utknapp och logga.
     */
    public AdminMenuPanel(){
        todaysDat = new WebFetching();
        JLabel time = new JLabel();
        time.setBounds(5, 60, 100, 50);
        time.setFont(new Font(time.getFont().getName(), Font.BOLD, 18));
        Timer timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                time.setText(clockFormat.format(new Date()));
                time.repaint();
            }
        });
        time.setText(clockFormat.format(new Date()));
        timer.start();

        JLabel date = new JLabel();
        date.setBounds(5, 5, 100, 50);
        date.setText(todaysDat.getDate());
        date.setFont(new Font(date.getFont().getName(), Font.BOLD, 18));

        JPanel datepanel = new JPanel();
        datepanel.setLayout(new BoxLayout(datepanel, BoxLayout.Y_AXIS));
        datepanel.add(date);
        datepanel.add(time);
        
        AllButtons homeButton = new AllButtons(size.MEDIUM, "Home");
        buttons.add(homeButton);
        AllButtons logoutButton = new AllButtons(size.MEDIUM, "Logout");
        buttons.add(logoutButton);
        JLabel logo = new JLabel(logga, JLabel.CENTER);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        add(datepanel, c);

        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.3;
        c.ipadx = 10;
        c.gridx = 1;
        c.gridy = 0;
        add(homeButton, c);

        c.gridx = 2;
        c.gridy = 0;
        add(logoutButton, c);

        c.weightx = 0;
        c.gridx = 3;
        c.gridy = 0;
        add(logo, c);
        setVisible(true);
    }

    /**
     * @return En arraylist med alla knappar som ligger i view.
     */
    public ArrayList<AllButtons> getButtons(){ return buttons; }
}
