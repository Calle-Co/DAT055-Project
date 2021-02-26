package admin.clientsInfo;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import global.AllButtons;
import global.AllButtons.*;
import admin.AdminMenuPanel;

/**
 * @author Carl Classon
 * @version 2021-02-26
 */
@SuppressWarnings("serial")
public class ClientsInfoView extends JPanel{
    private JPanel usersPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public ClientsInfoView(){
        usersPanel = new JPanel();
        usersPanel.setLayout(new GridLayout(20,1));
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        AdminMenuPanel menuPanel = new AdminMenuPanel();
        for (AllButtons b : menuPanel.getButtons()){
            buttons.add(b);
        }
        AllButtons updateButton = new AllButtons(size.LARGE, "Update");
        buttons.add(updateButton);
        topPanel.add(updateButton);
        topPanel.add(menuPanel);

        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(new JScrollPane(usersPanel));
        setVisible(true);
      
    }  
    
    /**
     * Lägger till alla användare i en panel.
     * @param users användarnamnen hämtade från databasen
     */
    public void setUsers(ArrayList<String> users){
        for(String user : users){
            AllButtons button = new AllButtons(size.SMALL, user);
            usersPanel.add(button);
        }
        usersPanel.revalidate();
        usersPanel.repaint();
    }

    public void clearUsers(){
        usersPanel.removeAll();
        usersPanel.revalidate();
        usersPanel.repaint();
    }

    public ArrayList<AllButtons> getButtons() { return buttons; }

}
