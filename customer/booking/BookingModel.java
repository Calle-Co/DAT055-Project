package customer.booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import global.InfoHolding;
import global.ServerConnection;

/**
 * Denna modellen sköter all komunikation med databasen som används inom MVC:n bookings.
 * @author Simon Länsberg, William Husar
 * @version 2021-03-03
 */
public class BookingModel {
    private ServerConnection serverConnection;

    /**
     * Denna metod används för att boka säten och informera databasen om bokningen
     * @param info Listan med alla säten som skall bokas.
     * @throws SQLException Om bokningen inte går att utföra kastas denna exception.
     * @throws ClassNotFoundException Behöver fångas men kommer aldrig kastas.
     */
	public void setBooked(ArrayList<InfoHolding> info) throws SQLException, ClassNotFoundException {
        for(InfoHolding i : info){
            serverConnection = new ServerConnection();
            serverConnection.DatabaseConnection();
            try(PreparedStatement ps = serverConnection.getConn().prepareStatement("INSERT INTO seats VALUES(?,?,?,?,?,?)");)
            {
                ps.setInt(1, Integer.parseInt(i.getFID())); 
                ps.setString(2, i.getOwner());
                ps.setString(3, i.getSeat());
                ps.setBoolean(4, true);
                ps.setString(5, i.getName());
                ps.setInt(6, Integer.parseInt(i.getAge()));
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new SQLException();
                //System.out.println(serverConnection.getError(e));
            }
            finally{
                serverConnection.getConn().close();
            }
        }
    }
    
    /**
     * Metod som hämtar antalet säten för en specifik modell av flygplan
     * @param flight_id Id numret för flygplanet i fråga
     */
    public int getSeatsNo(String flight_id) throws SQLException, ClassNotFoundException{
        int seats = -1;
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT seats FROM planes WHERE model = (SELECT model FROM flights WHERE flight_id = ?)");) {
            ps.setInt(1, Integer.parseInt(flight_id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                seats = rs.getInt("seats");
            }
        }
        catch (SQLException e) {
            throw new SQLException();
            //System.out.println(serverConnection.getError(e));
        }
        finally{
            serverConnection.getConn().close();
        }
        return seats;
    }

    /**
     * Metod som hämtar antalet bokade säten för ett specifikt flyg
     * @param flight_id Id numret för flygplanet i fråga
     * @return En ArrayList med alla bokade sätens nummer.
     * @throws SQLException Om något skulle gå fel vid hämtningen kastas ett exception
     * @throws ClassNotFoundException Behöver fångas men kommer aldrig kastas.
     */
    public ArrayList<String> getBookedSeats(String flight_id) throws SQLException, ClassNotFoundException{
        ArrayList<String> bookedSeats = new ArrayList<>();
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT seat FROM seats WHERE flight_id = ?");) {
            ps.setInt(1, Integer.parseInt(flight_id));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                bookedSeats.add(rs.getString("seat"));
            }
        }
        catch (SQLException e) {
            throw new SQLException();
            //System.out.println(serverConnection.getError(e));
        }

        finally{
            serverConnection.getConn().close();
                }
        return bookedSeats;
    }
}
