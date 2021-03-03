package customer.booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import global.InfoHolding;
import global.ServerConnection;

/**
 * @author Simon Länsberg, William Husar
 * @version 2021-02-24
 */
public class BookingModel {
    private ServerConnection serverConnection;

	public void setBooked(ArrayList<InfoHolding> info) throws SQLException, ClassNotFoundException {
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        for(InfoHolding i : info){
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
        }
        serverConnection.getConn().close();
    }
    
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
        serverConnection.getConn().close();
        return seats;
    }

}
