package customer.profile;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import global.ServerConnection;

/**
 * Denna klass sköter kommunkationen mellan programmet och databasen,
 * specifikt så raderar den en användare och dess bokningar från databasen.
 * @author Anna Manfredsson
 * @version 2021-03-05
 */
public class ProfileModel {
    private ServerConnection s;

    /**
     * Denna metod har som uppgift att ta bort en användare från databasen.
     * @param username Användaren som du vill ta bort från databasen.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
    public void deleteUser(String username) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("DELETE FROM customers WHERE username = ?");){
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            s.getConn().close();
        }   
    }

    /**
     * Denna metod har som uppgift att ta bort en användares bokningar från databasen.
     * @param username Användaren vars bokningar du vill ta bort från databasen.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteBookings(String username) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("DELETE FROM Seats WHERE customer = ?");){
            ps.setString(1, username);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close(); 
	}
    
}
