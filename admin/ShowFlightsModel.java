package admin;

import java.sql.*;
import global.ServerConnection;

public class ShowFlightsModel {
    private ServerConnection s;

    public ShowFlightsModel(){

    }
    
    public void addFlight(String flight_id, String from_d, String to_d, String date_of, String time_of) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("INSERT INTO flights VALUES (?,?,?,?,?)");)
        {
            ps.setString(1, flight_id);
            ps.setString(2, from_d);  
            ps.setString(3, to_d); 
            ps.setString(4, date_of);
            ps.setString(5, time_of);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close();   
    }
}
