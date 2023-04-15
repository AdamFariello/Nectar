package databaseCode;

import java.sql.*;

public class databaseConnection{
	private static String username  = "admin";
	private static String password  = "password";
	private String server    		= "nectar";
	private String host		 		= "localhost";
	private static String url;
	private static Connection connection = null;
	
	
	public databaseConnection() {
		//Default connection 
		init();
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
		init();
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password, String host) {
		this.username = username;
		this.password = password;
		this.host     = host;
		init();
	}
	public void init () {
		//Setup Url
		String s 		 = "jdbc:mysql://%s:%s/%s";
		String port		 = "3306";
		String mysqlURL  = String.format(s, host, port, server);
		
		//Setting up arguments
		//TODO: Add back time-zone and other arguments
		/*
		s = "?"
		  + "useUnicode=true&useJDBCCompliant"
		  + "TimezoneShift=true&useLegacyDatetimeCode=false"
		  + "&serverTimezone=%s";
		*/
		s = "?"
		  + "verifyServerCertificate=false";
		String timeZone  = "EDT";
		String mysqlArgs = String.format(s, timeZone);
		
		//Combine
		url = mysqlURL + mysqlArgs;
	}
	
	//Connections setups
	@SuppressWarnings("finally")
	public static Connection Connection() {
		try	{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return connection;	
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