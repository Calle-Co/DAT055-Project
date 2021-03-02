package admin.clientsInfo;

import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import global.AllButtons;
import global.AllButtons.*;
import admin.AdminMenuPanel;


/**
 * En klass som är ansvarig för grafikdelen av ClientsInfo.
 * @author Carl Classon
 * @version 2021-02-26
 */
@SuppressWarnings("serial")
public class ClientsInfoView extends JPanel{
    private JPanel clientsPanel;
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ArrayList<AllButtons> clients = new ArrayList<>();

    public ClientsInfoView(){
        clientsPanel = new JPanel();
        clientsPanel.setLayout(new GridLayout(20,1));
        
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
        add(new JScrollPane(clientsPanel));
        setVisible(true);
      
    }  
    
    /**
     * Lägger till alla användare i en panel.
     * @param users användarnamnen hämtade från databasen
     */
    public void setUsers(ArrayList<String> users){
        for(String user : users){
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new GridLayout(1,1));
           
            Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            Border blackLine = BorderFactory.createLineBorder(Color.black);
            CompoundBorder line = new CompoundBorder(empty, blackLine);
            Border grid1Border = BorderFactory.createTitledBorder(line, "Client username:");
            
            AllButtons userButton = new AllButtons(size.SMALL,user);
            clients.add(userButton);
            
            userPanel.add(userButton);
            userPanel.setBorder(grid1Border);
            clientsPanel.add(userPanel);
        }
        Border empty = BorderFactory.createEmptyBorder(0, 300, 0, 300);
        clientsPanel.setBorder(empty);
        clientsPanel.revalidate();
        clientsPanel.repaint();
    }

    public void clearUsers(){
        clientsPanel.removeAll();
        clientsPanel.revalidate();
        clientsPanel.repaint();
    }

    /**
     * Denna metod har till uppgift att starta en popup ruta som frågar
     * admin om man radera den valda användaren eller inte.
     * @param username Användaren som man har tryckt på
     * @return
     */
    public boolean clientPopup(String username){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        String s = "Do you want to delete the user " + username + "?";
        int dialogResult = JOptionPane.showConfirmDialog (null, s,"Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            return true;
        } else {
            return false;
        } 
    }

    public ArrayList<AllButtons> getButtons() { return buttons; }
    public ArrayList<AllButtons> getClient() { return clients; }


}
