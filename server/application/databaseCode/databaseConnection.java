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
		username = "Yocif";
		password = "1234";		
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url 	  = url;
	}
	@SuppressWarnings("deprecation")
	public void init () {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			url = "jdbc:mysql://localhost:3306/" + server
				+ "?useUnicode=true&useJDBCCompliant"
				+ "TimezoneShift=true&useLegacyDatetimeCode=false"
				+ "&serverTimezone=" + timeZone
			;
		}
	}
	
	
	//Connections setups
	@SuppressWarnings("finally")
	public static Connection Connection() {
		try	{
			return connection = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return null;	
		}
	}
	public void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
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