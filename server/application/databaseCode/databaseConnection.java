package databaseCode;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

//Databases:
//	1) nectarDB_administration
//	2) nectarDB_products
//	3) nectarDB_user

@SuppressWarnings("serial")
class errorUnknownDatabase extends Exception {
	private static String error = "The database %s is not a real database \n"
								+ "Use one of the following databases: "
								+ "\n\t 1) nectarDB_administration"
								+ "\n\t 2) nectarDB_products"
								+ "\n\t 3) nectarDB_user";
	private static String errorBorder = "=".repeat(error.length() * 2);			
	
	public errorUnknownDatabase() {}
	public errorUnknownDatabase(String database) {
		super (String.format(error + "\n" + errorBorder, database));
	}
}

public class databaseConnection{
	private static String username  = "admin";
	private static String password  = "password"; 
	private static String host	    = "localhost";
	private static String currentDB 	 = "nectarDB_user";	
	private static String url 			 = null;
	private static Connection connection = null;
	
	
	public databaseConnection() {}
	@SuppressWarnings("static-access") 
	public databaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
	}
	@SuppressWarnings("static-access")
	public databaseConnection(String username, String password, String host) {
		this.username = username;
		this.password = password;
		this.host     = host;
	}

	
	public static Connection startConnection() {return startConnection(currentDB);}
	@SuppressWarnings("finally") 
	public static Connection startConnection(String database) {
		try	{			
			if(checkDatabase(database) == false) {
				
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			setURL(database);
			connection = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return connection;	
		}
	}
	public void endConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public String getURL () { return url; }
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
	
		
	public String getCurrentServer() {return currentDB;}
	@SuppressWarnings("finally")
	public String setCurrentServer(String newDatabase) {
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
		List<String> dataBaseList = new ArrayList<>(Arrays.asList(
				"nectarDB_administration", 
				"nectarDB_products", 
				"nectarDB_user"
			));
		return dataBaseList.toString().contains(database);
	}

	
	@SuppressWarnings("finally") 
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
		} finally {
			System.out.println(string);
			return string;
		}
	}
}