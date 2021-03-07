package customer.myBookings;

import java.sql.*;
import java.util.ArrayList;
import customer.flight.FlightInfoButton;
import global.ServerConnection;

/**
 * En klass för kommunkation mellan programmet och databasen. 
 * Specifikt hämta alla flygningar och bokningar som finns i databasen.
 * @author Simon Länsberg & William Husar
 * @version 2021-03-04
 */
public class MyBookingModel{
    private ServerConnection serverConnection;

    public MyBookingModel(){
       
    }

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