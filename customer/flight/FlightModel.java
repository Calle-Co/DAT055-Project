package customer.flight;

import java.sql.*;
import global.ServerConnection;

public class FlightModel {
    private ServerConnection serverConnection;
    private String[] flight;

    public FlightModel() {
        this.flight = new String[5];
    }

    /**
     * 
     * @param username användarnamnet som skrivs in i rutan.
     * @param password lösenordet som skrivs in i rutan.
     * @return returnerar true om det finns en användare med det lösenordet, annars false.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String[] getFlight(String from, String to, String date) throws SQLException, ClassNotFoundException{
        serverConnection = new ServerConnection();
        serverConnection.DatabaseConnection();
        try(PreparedStatement ps = serverConnection.getConn().prepareStatement("SELECT * FROM flights WHERE from_d = ? AND to_d = ? AND date_of >= ? ORDER BY (date_of, time_of) ASC");)
        {
            ps.setString(1, from); 
            ps.setString(2, to);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                flight[0] = rs.getString("flight_id");
                flight[1] = rs.getString("from_d");
                flight[2] = rs.getString("to_d");
                flight[3] = rs.getString("date_of");
                flight[4] = rs.getString("time_of");
            } 
        }
        catch (SQLException e) {
            //throw new SQLException();
            System.out.println(serverConnection.getError(e));
        }
        serverConnection.getConn().close();
        return flight;
    }
}