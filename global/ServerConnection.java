package global;

import java.sql.*;
import java.util.Properties;

/**
 * Klassen används för att skapa en anslutning till programmets databas.
 * Koden är från en laboration i kursen Databaser TDA357.
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
     * Konstruktorn skapar en anslutning till databasen med hjälp
     * av 3 parametrar.
     * @param db Adress till servern som databasen befinner sig på.
     * @param user Användarnamn som används för autentisering mot servern. 
     * @param pwd Lösenord som används för autentisering mot servern. 
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
   
    /**
     * Metoden skriver ut eventuella error på ett mer läsbart sätt.
     * @param e Det SQLException som ska hanteras.
     * @return En string som förklarar SQL-error meddelandet.
     */
    public String getError(SQLException e){
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"","\\\"");
        return message;
    }

    /**
     * @return Anslutning till databasen.
     */
    public Connection getConn(){
        return this.conn;
    }
}
