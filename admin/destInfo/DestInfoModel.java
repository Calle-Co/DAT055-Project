package admin.destInfo;

import java.sql.*;
import java.util.ArrayList;

import global.Destination;
import global.ServerConnection;

public class DestInfoModel {
    private ServerConnection s;
    private ArrayList<Destination> destinations;

    public DestInfoModel(){
        destinations = new ArrayList<>();
    }

    public ArrayList<Destination> fetchDest() throws SQLException, ClassNotFoundException{
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

    public void addDestination(String dest, String abbrev) throws SQLException, ClassNotFoundException{
        s = new ServerConnection();
        s.DatabaseConnection();
        try(PreparedStatement ps = s.getConn().prepareStatement("INSERT INTO destinations VALUES (?,?)");)
        {
            ps.setString(1, dest);
            ps.setString(2, abbrev);  
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        s.getConn().close();
    }

    public void clearDest(){ destinations.removeAll(destinations);}
}
