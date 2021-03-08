package customer.myBookings;

import java.sql.*;
import java.util.ArrayList;
import customer.flight.FlightInfoButton;
import global.ServerConnection;

/**
 * En klass för kommunkation mellan programmet och databasen. 
 * Specifikt hämta alla flygningar och bokningar som finns i databasen.
 * @author Simon Länsberg, William Husar
 * @version 2021-03-04
 */
public class MyBookingModel{
    private ServerConnection serverConnection;

    /**
     * Denna metod kommunicerar med databasen, den hämtar hem alla flyg med ett specifikt id
     * och returnernar dem som en lista av FlightInfoButtons.
     * @param flight_ids Det id av flyg som ska hämtas
     * @return null om databasen inte har några flyg med det specifika id. Om inget exception förekommit returneras listan med hämtade flyg. 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<FlightInfoButton> getFlight(ArrayList<String> flight_ids) throws SQLException, ClassNotFoundException{
        ArrayList<FlightInfoButton> flights = new ArrayList<>();
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();

        for(String f : flight_ids ){

            try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT * FROM flights WHERE flight_id = ? ORDER BY (date_of, time_of) ASC");)
            {
                int n = 0;
                ps.setInt(1, Integer.parseInt(f)); 
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    flights.add(new FlightInfoButton(rs.getString("flight_id"), rs.getString("from_d"), rs.getString("to_d"),
                                        rs.getString("date_of"), rs.getString("time_of")));
                    n++;
                } 
                if(n == 0){
                    return null;

                }
            }
            catch (SQLException e) {
                //throw new SQLException();
                System.out.println(serverConnection.getError(e));
            }
        }
        serverConnection.getConn().close();
        return flights;
    }

    /**
     * Denna metod kommunicerar med databasen, den hämtar hem alla bokningar för en specifik användare
     * och returnernar alla flygid i en lista.
     * @param user En användare vars bokningar ska hämtas.
     * @return listan av flyg som hämtats.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<String> getBookings(String user) throws SQLException, ClassNotFoundException{
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        ArrayList<String> flights = new ArrayList<>();

        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT DISTINCT flight_id FROM seats WHERE customer = ?");)
        {
            ps.setString(1, user); 
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                flights.add(rs.getString("flight_id"));
            }
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        finally{
            serverConnection.getConn().close();
        }
        return flights; 
    }

    /**
     * Denna metod kommunicerar med databasen, den tar bort alla bokade flyg för den valda användaren med det valda id:t.
     * @param user Den användare vars bokade flyg ska tas bort.
     * @param fid Det flygid:t som ska tas bort.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
	public void deleteBooking(String user, String fid) throws SQLException, ClassNotFoundException{
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("DELETE FROM Seats WHERE customer = ? AND flight_id = ?");){
            ps.setString(1, user);
            ps.setInt(2, Integer.parseInt(fid)); 
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new SQLException();
        }
        serverConnection.getConn().close(); 
	}
}