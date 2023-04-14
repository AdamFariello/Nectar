package databaseCode;

import java.sql.*;

public class databaseConnection{
	private String server   = "Nectar";
	private static String username, password, url;
	private static Connection connection;
	
	//Connections
	public databaseConnection() {
		//Default connection 
		username = "Yocif";
		password = "1234";	
		init("localhost");
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
		init("localhost");
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password, String url) {
		this.username = username;
		this.password = password;
		init(url);
	}
	@SuppressWarnings("deprecation")
	public void init (String serverLocation) {		
		try {
			//TODO: Figure out what to do with these:
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//TODO: Add case to check for bad entries
			switch (serverLocation) {
				case "local":
					String localhost  = "localhost";
					String port		  = "3306";
					String timeZone   = "EDT";
					String serverUrl  = String.format(
											"jdbc:mysql://%s:%s/%s",
										 	localhost, port, server
									    );
					String serverArgs = String.format(
											"?useUnicode=true&useJDBCCompliant"
										  + "TimezoneShift=true&useLegacyDatetimeCode=false"
										  + "&serverTimezone=%s",
										    timeZone
									  );
					url = serverUrl + serverArgs;
					
					/*
					url = "jdbc:mysql://localhost:3306/" + server
						+ "?useUnicode=true&useJDBCCompliant"
						+ "TimezoneShift=true&useLegacyDatetimeCode=false"
						+ "&serverTimezone=" + timeZone
					;
					*/
					break;
					
				default:
					//TODO: Replace with ipaddress/hostname of system
					url = serverLocation;
					break;
			}
		} catch (Exception e) {
			System.out.println(e);
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