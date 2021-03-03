package admin.flightsInfo;

import java.sql.*;
import java.util.ArrayList;

import customer.flight.FlightInfoButton;
import global.Destination;
import global.ServerConnection;

public class FlightsInfoModel {
    private ServerConnection s;
    private ArrayList<Destination> destinations;
    //private ArrayList<FlightInfoButton> flights;

    public FlightsInfoModel(){
        this.destinations = new ArrayList<>();
        //this.flights = new ArrayList<>();
    }

    public ArrayList<Destination> getDestinations() throws SQLException, ClassNotFoundException {
        s = new ServerConnection();
        s.DatabaseConnection();
        try(Statement st = s.getConn().createStatement();)
        {
            ResultSet rs = st.executeQuery("SELECT * FROM destinations ORDER BY destination ASC");
            while(rs.next()){
                destinations.add(new Destination(rs.getString("destination"),rs.getString("abbreviation")));
            }
        }
        catch(SQLException e) {
            throw new SQLException();
        } 
        s.getConn().close(); 
        return destinations; 
    }
    
    public void addFlight(String from_d, String to_d, String date_of, String time_of, String model) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("INSERT INTO flights VALUES (DEFAULT,?,?,?,?,?)");)
        {
            ps.setString(1, from_d);
            ps.setString(2, to_d);  
            ps.setDate(3, java.sql.Date.valueOf(date_of)); 
            ps.setDate(4, java.sql.Date.valueOf(time_of));
            ps.setString(5, model);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close();   
    }
}
