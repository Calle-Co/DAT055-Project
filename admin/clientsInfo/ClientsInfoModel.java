package admin.clientsInfo;

import java.sql.*;
import java.util.ArrayList;
import global.ServerConnection;

/**
 * En klass för kommunikation mellan programet och databasen.
 * @author Carl Classon
 * @version 2021-02-26
 */ 
public class ClientsInfoModel{
    private ServerConnection s;
    private ArrayList<String> users;

    public ClientsInfoModel(){
        users = new ArrayList<>();
    }

    /**
     * Denna metod hämtar användarnamnen på alla kunder som finns i databasen.
     * @return En lista med namnen på alla användarnamn i databasen.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
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
        s.getConn().close(); 
        return users; 
    }


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
        }catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close(); 
    }

    public void clearUsers(){
        users.removeAll(users);
    }


}