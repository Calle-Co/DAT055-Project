package admin.flightsInfo;

import java.sql.*;
import java.util.ArrayList;
import global.Destination;
import global.Flight;
import global.ServerConnection;

/**
 * Denna metod ansvarar för kommunkationen mellan FlightsInfo-delen av programmet och databasen.
 * @author Anna Manfredsson, Carl Classon
 * @version 2021-03-04
 */
public class FlightsInfoModel {
    private ServerConnection s;
    private ArrayList<Destination> destinations;
    private ArrayList<Flight> planeModels;

    public FlightsInfoModel(){
        destinations = new ArrayList<>();
        planeModels = new ArrayList<>();
    }

    /**
     * Denna metod hämtar hem alla destinatoner från databasen.
     * @return En ArrayList med alla destinationer som ligger i databasen.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
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
        } finally {
            s.getConn().close();  
        }  
        return destinations; 
    }

    /**
     * Denna metod hämtar hem alla flygplansmodeler från databasen.
     * @return En ArrayList med alla destinationer som ligger i databasen.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
    public ArrayList<Flight> getPlaneModels() throws SQLException, ClassNotFoundException {
        s = new ServerConnection();
        s.DatabaseConnection();
        try(Statement st = s.getConn().createStatement();)
        {
            ResultSet rs = st.executeQuery("SELECT model FROM planes ORDER BY model ASC");
            while(rs.next()){
                planeModels.add(new Flight(rs.getString("model")));
            }
        }
        catch(SQLException e) {
            throw new SQLException();
        } finally {
            s.getConn().close();  
        } 
        return planeModels; 
    }
    
    /**
     * Denna metod försöker lägga till en ny flygning i databasen.
     * @param from_d Destinationen som man vill att planet skall åka till.
     * @param to_d  Destinationen som man vill att planet skall åka från.
     * @param date_of Datumet för avfärd.
     * @param time_of Tiden för avfärd.
     * @param model Modellen man vill att flygplanet skall ha.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
    public void addFlight(String from_d, String to_d, String date_of, String time_of, String model) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("INSERT INTO flights VALUES (DEFAULT,?,?,?,?,?)");)
        {
            ps.setString(1, from_d);
            ps.setString(2, to_d);  
            ps.setDate(3, java.sql.Date.valueOf(date_of)); 
            ps.setTime(4, java.sql.Time.valueOf(time_of));
            ps.setString(5, model);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        } finally {
            s.getConn().close();  
        } 
    }
    
}
