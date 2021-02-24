package start;

import java.sql.*;
import global.ServerConnection;

/**
 * Denna klass har som uppdrag att logga in en admin.
 * Lösenordet måste stämma för att kunna logga in.
 * 
 * @author Carl Classon
 * @version 2021-02-24
 */
public class AdminLoginModel{
    private ServerConnection s;

    /**
     * Metod för att jämnföra det inskrivna lösenordet med det som finns i databasen.
     * 
     * @param password det inskrivna lösenordet
     * @return  true om lösenordet matchar det i databasen, annars false.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean adminLogin(String password) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("SELECT * FROM admin WHERE username = ?");)
        {
            ps.setString(1, "admin");  
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString("password").equals(password)){ 
                    return true;
                }
                else 
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