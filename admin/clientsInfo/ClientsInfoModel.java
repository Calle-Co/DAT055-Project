package admin.clientsInfo;

import java.sql.*;
import java.util.ArrayList;

import global.ServerConnection;

/**
 * @author Carl Classon
 * @version 2021-02-26
 */ 
public class ClientsInfoModel{
    private ServerConnection s;
    private ArrayList<String> users;

    public ClientsInfoModel(){
        users = new ArrayList<>();
    }

    public ArrayList<String> getUsers() throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();

        try(Statement st = s.getConn().createStatement();)
        {
            ResultSet rs = st.executeQuery("SELECT * FROM customers ORDER BY username ASC");
            while(rs.next()){
                users.add(rs.getString("username"));
            }
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        return users; 
    }

    public void clearUsers(){
        users.removeAll(users);
    }

}