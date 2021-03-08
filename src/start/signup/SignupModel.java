package start.signup;

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

    /**
     * Denna metod lägger till en användare i databasen.
     * @param username användarnamnet som används för att registrera en användare.
     * @param password lösenordet som användaren väljer.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
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
        } finally {
            s.getConn().close();
        }   
    }
}