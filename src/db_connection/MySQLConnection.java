package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    
    private static String ConnURL = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
    private static String username = "YOUR_DB_USERNAME";
    private static String password = "YOUR_DB_PASSWORD";
    
    private static String forNameClassVar = "com.mysql.cj.jdbc.Driver";
    
    public static Connection DBConn() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(ConnURL, username, password);
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return con;
    }
}
