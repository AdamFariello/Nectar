package databaseCode;

import java.sql.*;

public class databaseConnection{
	private String server   = "Nectar";
	private String timeZone = "EDT";
	private static String username, password, url;
	private static Connection connection;
	
	//Connections
	public databaseConnection() {
		//Default connection 
		//Login is from server/database/.notes
		username = "Yocif";
		password = "1234";
		url 	 = "jdbc:mysql://localhost:3306/" + server
				 + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + timeZone
		;
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
		url 	 = "jdbc:mysql://localhost:3306/" + server
				 + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + timeZone
		;
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url 	  = url;
	}
	
	
	//Connections setups
	public static Connection Connection() {
		try	{
			return connection = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e1) {
			System.out.println(e1);
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}
	
	
	
	//Gets
	public Connection getConnection() {
		return connection;
	}
	public String getServerName() {
		return server;
	}
	
	
	//Inherited
	public String toString() {
		String string = null;
		try {
			string = 
				  "Url: " 		 +url		 +"\n"
				+ "UserName: "	 +username	 +"\n"
				+ "Password: "	 +password	 +"\n"
				+ "Connection: " +connection +"\n"
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return string;
	}
}