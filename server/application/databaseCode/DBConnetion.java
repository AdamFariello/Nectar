package databaseCode;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DBConnetion{
	private static String username  = "admin";
	private static String password  = "password"; 
	private static String host	    = "localhost";
	private static String currentDB 	 = "nectarDB_user";	
	private static String url 			 = null;
	private static Connection connection = null;

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
	public static Connection getConnection () {return connection;}
	@SuppressWarnings("finally") 
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
		} finally {
			return (connection = null);	
		}
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
	private static String setURL (String server) {
		//Direct connection
		String s 		  = "jdbc:mysql://%s:%s/%s";
		String port   	  = "3306";
		String mysqlURL   = String.format(s, host, port, server);
		
		//Arguments
		//TODO: Add back time-zone and other arguments
		//Arguments to bring back:
		//	1) "useUnicode=true&useJDBCCompliant"
		//	2) "TimezoneShift=true&useLegacyDatetimeCode=false"
		//	3) "&serverTimezone=%s"
		s = "?" + "verifyServerCertificate=false";
		String timeZone   = "EDT";
		String mysqlArgs = String.format(s, timeZone);
		
		//TODO: Copy paste format and example of sql line
		return (url = mysqlURL + mysqlArgs);
	}
		
	//Server Methods
	public static String getCurrentServer() {return currentDB;}
	@SuppressWarnings("finally")
	public static String setCurrentServer(String newDatabase) {
		try { 
			if (checkDatabase(newDatabase)) {
				return (currentDB = newDatabase);
			} else {
				throw new errorUnknownDatabase(newDatabase);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return null;
		}
	}
	public static Boolean checkDatabase(String database) {		
		String [] dataBaseList = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
		
		for (int i = 0; i < dataBaseList.length; i++)
			if (dataBaseList[i] == database)
				return true;
		return false;
	}

	//Misc.
	@SuppressWarnings("finally") 
	public String toString() {
		String stringToPrint = null;
		try {
			String s = "UserName: %s\n" 
					 + "Password: %s\n" 
					 + "URL: %s\n" 
					 + "Connection: %s\n" 
					 + "Current Database: %s\n";
			stringToPrint = String.format(s, username, password, url, connection, currentDB);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(stringToPrint);
			return stringToPrint;
		}
	}
}