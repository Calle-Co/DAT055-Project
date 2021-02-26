package start.login;

import java.sql.*;
import global.ServerConnection;

/**
 * Denna klassen sköter inloggning av användare.
 * Den matchar användarnamn och lösenord mot databasen.
 * 
 * @author Carl Classon
 * @version 2021-02-24
 */
public class LoginModel {

    ServerConnection s = new ServerConnection();

    /**
     * 
     * @param username användarnamnet som skrivs in i rutan.
     * @param password lösenordet som skrivs in i rutan.
     * @return returnerar true om det finns en användare med det lösenordet, annars false.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean userLogin(String username, String password) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("SELECT * FROM customers WHERE username = ? AND password = ?");)
        {
            ps.setString(1, username); 
            ps.setString(2, password); 
            ResultSet rs = ps.executeQuery();
            String u = "";
            String p = "";
            while(rs.next()){
                u = rs.getString("username");
                p = rs.getString("password"); 
            }
            if(u.equals(username) && p.equals(password)){
                return true;
            } else {
                return false;
            }    
        }
        catch (SQLException e) {
            //throw new SQLException();
            System.out.println(s.getError(e));
        }
        s.getConn().close();
        return true;
    }
}
