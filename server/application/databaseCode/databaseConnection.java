package databaseCode;

import java.sql.*;

public class databaseConnection{
	private static Connection connection;
	private static String username, password, url, server;
	
	//Connections
	public databaseConnection() {
		//Default connection 
		username = "admin";
		password = "password";	
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
	public void init (String serverLocation) {		
		//TODO: Add case to check for bad entries
		
		server = "nectar";
		switch (serverLocation) {
			case "localhost":
				String localhost  = "localhost";
				String port		  = "3306";
				String timeZone   = "EDT";
				String mysqlURL  = String.format(
										"jdbc:mysql://%s:%s/%s",
									 	localhost, port, server
								    );
				String mysqlArgs = String.format(
										"useUnicode=true&useJDBCCompliant"
									  + "TimezoneShift=true&useLegacyDatetimeCode=false"
									  + "&serverTimezone=%s",
									    timeZone
								  );
				url = mysqlURL + "?" + mysqlArgs;
				break;
				
			default:
				//TODO: Replace with ipaddress/hostname of system
				url = serverLocation;
				break;
		}
	}
	
	
	//Connections setups
	@SuppressWarnings("finally")
	public static Connection Connection() {
		try	{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);
			return connection;
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