package admin.destInfo;

import java.sql.*;
import java.util.ArrayList;
import global.Destination;
import global.ServerConnection;


/**
 * En klass för kommunkation mellan programmet och databasen.
 * @author Carl Classon
 * @version 2021-03-02
 */
public class DestInfoModel {
    private ServerConnection s;
    private ArrayList<Destination> destinations;

    public DestInfoModel(){
        destinations = new ArrayList<>();
    }

    /**
     * Denna metod kommunicerar med databasen, den hämtar hem alla destinationer
     * som ligger där för tillfället och returnernar dem som en lista.
     * @return En lista av destinationer, där alla destinationer har ett namn och en förkortning. 
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
    public ArrayList<Destination> fetchDest() throws SQLException, ClassNotFoundException {
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
     * Denna metod försöker lägga till en ny destination och dess förkortning till databasen.
     * parametrarna fås ifrån användaren.
     * @param dest namnet på destinationen som användaren vill lägga till.
     * @param abbrev förkortningen av namnet.
     * @throws SQLException Om något går fel med SQL-anropet.
     * @throws ClassNotFoundException Om "ServerConnection.DatabaseConnection" skulle kalla på en klass som ej existerar.
     */
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
        } finally {
            s.getConn().close();
        }     
    }

    /**
     * Denna metod rensar alla destinationer ifrån listan för att undvika 
     * duplicerade värden.
     */
    public void clearDest(){ destinations.removeAll(destinations);}
}
