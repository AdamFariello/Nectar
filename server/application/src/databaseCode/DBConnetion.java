package databaseCode;

import java.sql.*;

public class DBConnetion{
	private static String username  = "admin";
	private static String password  = "password"; 
	private static String host	    = "localhost";
	private static String currentDB 	 = "nectarDB_user";	
	private static String url 			 = null;
	
	private static Connection connection = null;
	private final static String [] dbs = {
		"nectarDB_administration", 
		"nectarDB_products", 
		"nectarDB_user"
	};
	
	//Class Functions
	public DBConnetion() {}
	@SuppressWarnings("static-access") 
	public DBConnetion(String username, String password) {
		this.username = username;
		this.password = password;
	}
	@SuppressWarnings("static-access")
	public DBConnetion(String username, String password, String host) {
		this.username = username;
		this.password = password;
		this.host     = host;
	}

	//Connections Methods
	public Connection getConnection () {return connection;}
	public Connection startConnection(String database) {
		try	{			
			if(checkDatabase(database)) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				setURL(database);
				return (connection = DriverManager.getConnection(url,username,password));
			} else { 
				throw new errorUnknownDatabase(database); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	public void endConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//URL Methods
	public String getURL() {return url;}
	private String setURL (String server, String timeZone) {
		//TODO Bring back these arguments:
		//	1) "useUnicode=true&useJDBCCompliant"
		//	2) "TimezoneShift=true&useLegacyDatetimeCode=false"
		//	3) "&serverTimezone=%s"
		String s = "?" + "verifyServerCertificate=false";
		return (url = setURL(server) + String.format(s, timeZone));
	}
	private String setURL (String server) {
		String s 		 = "jdbc:mysql://%s:%s/%s";
		String port   	 = "3306";
		return (url = String.format(s, host, port, server));
	}
		
	//Server Methods
	public String getCurrentServer() {return currentDB;}
	public String setCurrentServer(String newDatabase) {
		try { 
			if (checkDatabase(newDatabase)) {
				if (connection != null) {
					connection.setCatalog(newDatabase);
				}
				return (currentDB = newDatabase);
			} else {
				throw new errorUnknownDatabase(newDatabase);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		return null;
	}
	public static Boolean checkDatabase(String database) {			
		for (String s: dbs) {
			if (s == database) {return true;}
		}
		return false;
	}

	public String toString() {
		try {
			String s = "UserName: %s\n" 
					 + "Password: %s\n" 
					 + "URL: %s\n" 
					 + "Connection: %s\n" 
					 + "Current Database: %s\n";
			return String.format(s, username, password, url, connection, currentDB);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}