package db_connection;

import java.sql.Connection;

import java.sql.DriverManager;

public class MySQLConnection {
	
	private static String ConnURL = "jdbc:mysql://localhost:3306/smarketdb";
	private static String username = "root";
	private static String password = "#Root123";
	
	public static String forNameClassVar = "com.mysql.cj.jdbc.Driver";
	
	public static Connection DBConn() {
		Connection con = null;
		try {
			con=DriverManager.getConnection(ConnURL,username,password);
			}
		catch(Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		return con;
	}
	
}
