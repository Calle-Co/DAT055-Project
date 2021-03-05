package customer.home;

import java.sql.*;
import java.util.ArrayList;
import global.ServerConnection;
import global.Destination;

/**
 * En klass för kommunkation mellan programmet och databasen.
 * @author William Husar & Simon Länsberg
 * @version 2021-02-26
 */
public class HomeModel {
    private ServerConnection serverConnection;
    private ArrayList<Destination> destinations;

    public HomeModel() {
        this.destinations= new ArrayList<>();
    }

    /**
     * Denna metod kommunicerar med databasen, den hämtar hem alla destinationer
     * som ligger där för tillfället och returnernar dem som en lista.
     * @return En lista av destinationer, där alla destinationer har ett namn och en förkortning. 
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
    public ArrayList<Destination> getDestinations() throws SQLException, ClassNotFoundException{
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT * FROM Destinations ORDER BY destination ASC");)
        {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                destinations.add(new Destination(rs.getString("destination"), rs.getString("abbreviation")));
            } 
        }
        catch (SQLException e) {
            throw new SQLException();
        } finally {
            serverConnection.getConn().close();
        }
        return destinations;
    }
}