package global;
import java.sql.*;
import java.util.Properties;

/**
 * Denna klass hanterar kommunikationen med vår postgres server.
 * Det mesta av koden är taget från kursen "TDA357" på Chalmers.
 * 
 * @author Carl Classon
 * @version 2021-02-24
 */
public class ServerConnection {
    private static final String DATABASE = "jdbc:postgresql://dat055-db-4930.postgresql.dbs.scalingo.com:32956/dat055_db_4930";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private Connection conn;
    

    public void DatabaseConnection() throws SQLException, ClassNotFoundException {
        DatabaseConnection(DATABASE, USERNAME, PASSWORD);  
    }

    /**
     * 
     * @param db Adressen till servern (databasen) som man vill komma åt
     * @param user Användarnamnet som används för autentisering mot servern. 
     * @param pwd Lösenordet -||-
     * @throws SQLException Om lösenordet eller användarnamet skulle vara fel.
     * @throws ClassNotFoundException Om klassen "org.postgresql.driver" inte skulle finnas.
     */
    public void DatabaseConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        conn = DriverManager.getConnection(db, props);
    }

   
    /** Detta är taget från Lab4 i kursen TDA357 (Databaser)
     * 
     * @param e ett SQLException som man vill printa.
     * @return En string som förklarar SQL-error meddelandet.
     */
    public String getError(SQLException e){
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"","\\\"");
        return message;
    }

    public Connection getConn(){
        return this.conn;
    }
}
