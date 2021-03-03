package admin;

import java.sql.*;
import java.util.ArrayList;

import customer.flight.FlightInfoButton;
import global.Destination;
import global.ServerConnection;

public class ShowFlightsModel {
    private ServerConnection s;
    private ArrayList<Destination> destinations;
    private ArrayList<FlightInfoButton> flights;

    public ShowFlightsModel(){
        this.destinations = new ArrayList<>();
        this.flights = new ArrayList<>();

    }

    public ArrayList<FlightInfoButton> getFlights() throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        flights.clear();
        try(PreparedStatement ps = s.getConn().prepareStatement("SELECT * FROM flights ORDER BY (date_of, time_of) ASC");)
        {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                flights.add(new FlightInfoButton(rs.getString("flight_id"), rs.getString("from_d"), rs.getString("to_d"),
                                    rs.getString("date_of"), rs.getString("time_of")));
            }
        }
        catch (SQLException e) {
            throw new SQLException();
            //System.out.println(s.getError(e));
        }
        return flights;
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
        return destinations; 
    }
    
    public void addFlight(String from_d, String to_d, String date_of, String time_of, String model) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("INSERT INTO flights VALUES (?,?,?,?,?)");)
        {
            ps.setString(1, from_d);
            ps.setString(2, to_d);  
            ps.setString(3, date_of); 
            ps.setString(4, time_of);
            ps.setString(5, model);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close();   
    }
}
