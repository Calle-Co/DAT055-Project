package start;

import java.sql.*;
import global.ServerConnection;

/**
 * Denna klassen är ansvarig för att registrera kunder in i databasen.
 * 
 * @author Carl Classon, William Husar
 * @version 2021-02-24
 */
public class SignupModel {
    private ServerConnection s;

    public SignupModel() {}
    
    public void signUp(String username, String password) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("INSERT INTO customers VALUES (?,?)");)
        {
            ps.setString(1, username);
            ps.setString(2, password);  
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close();   
    }
}

