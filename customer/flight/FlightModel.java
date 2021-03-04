package customer.flight;

import java.sql.*;
import java.util.ArrayList;
import global.ServerConnection;

/**
 * Klassen representerar model i det MVC som ansvarar
 * för att hämta och visa flyg som passar kundens önskemål.
 * @author William Husar, Simon Länsberg
 * @version 2021-03-02
 */
public class FlightModel {
    private ServerConnection serverConnection;
    private ArrayList<FlightInfoButton> flights;

    public FlightModel() {
        this.flights = new ArrayList<>();
    }

    /**
     * Metoden används för att hämta alla flyg som passar kundens önskemål
     * med hjälp av flera parametrar.
     * @return En ArrayList med knappar som innehåller information om relevanta flyg.
     * @param from Avgående destination.
     * @param to Ankommande destination.
     * @param date Datum för avgång.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
    public ArrayList<FlightInfoButton> getFlight(String from, String to, String date) throws SQLException, ClassNotFoundException {
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        flights.clear();
        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT * FROM flights WHERE from_d = ? AND to_d = ? AND date_of >= ? ORDER BY (date_of, time_of) ASC");)
        {
            int n = 0;
            ps.setString(1, from); 
            ps.setString(2, to);
            ps.setDate(3, java.sql.Date.valueOf(date));
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
        finally {
            serverConnection.getConn().close();
        }
        return flights;
    }
}