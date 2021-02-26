package customer.home;

import java.sql.*;
import java.util.ArrayList;

import global.ServerConnection;
import global.Destination;


public class HomeModel {
    private ServerConnection serverConnection;
    private ArrayList<Destination> destinations;

    public HomeModel() {
        this.destinations= new ArrayList<>();

    }

    /**
     * 
     * @param username användarnamnet som skrivs in i rutan.
     * @param password lösenordet som skrivs in i rutan.
     * @return returnerar true om det finns en användare med det lösenordet, annars false.
     * @throws SQLException
     * @throws ClassNotFoundException
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
            //throw new SQLException();
            System.out.println(serverConnection.getError(e));
        }
        serverConnection.getConn().close();
        return destinations;
    }
}
