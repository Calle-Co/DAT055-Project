package start;

import java.sql.*;
import java.util.Properties;

/**
 * @author Carl Classon, William Husar
 * @version 2021-02-24
 */
public class SignupModel {
    private static final String DATABASE = "jdbc:postgresql://dat055-db-4930.postgresql.dbs.scalingo.com:32956/dat055_db_4930";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private Connection conn;

    public SignupModel() { }
    
    public void DatabaseConnection() throws SQLException, ClassNotFoundException {
        DatabaseConnection(DATABASE, USERNAME, PASSWORD);  
    }

    // Initializes the connection, no need to change anything here
    public void DatabaseConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        conn = DriverManager.getConnection(db, props);
    }

    public void signUp(String username, String password) throws SQLException, ClassNotFoundException{
        DatabaseConnection();  

        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO customers VALUES (?,?)");)
        {
            ps.setString(1, username);
            ps.setString(2, password);  
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException();
        }
        conn.close();   
    }

    // Detta är taget från Lab4 i kursen TDA357 (Databaser)
    public String getError(SQLException e){
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"","\\\"");
        return message;
    }
}

