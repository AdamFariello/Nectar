package databaseCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Resources.Queue_LinkedList;

public class DatabaseConnection{
	private static String username, password, url;
	private String server   = "Nectar";
	private String timeZone = "EDT";
	
	public Connection connect() {
		//Default connection 
		//Login is from server/database/.notes
		username = "Aeyjaejay";
		password = "1234";
		url 	 = "jdbc:mysql://localhost:3306/" + server
				 + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + timeZone
		;
	}
	public DatabaseConnection(String username, String password) {
		this.username = username;
		this.password = password;
		url 	 = "jdbc:mysql://localhost:3306/" + server
				 + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=" + timeZone
		;
	}
	public DatabaseConnection(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url 	  = url;
	}
	
	public void close() /* throws SQLException, Exception */ {
		//TODO: Figure if code should just be thrown
		try {
			Connection().close();
		} catch (SQLException e1) {
			System.out.println(e1);
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}
	
	public static Connection Connection() throws Exception{
		try	{
			Connection con = DriverManager.getConnection(url,username,password);
			return con;
			
		}	catch(Exception e)	{
			System.out.println(e);
		}
		
		return null;
	}
	
	public String toString() { 
		String s = null;
		
		try {
			s = Connection()				+"\n"
				+ "Url: " 		+url		+"\n"
				+ "UserName: "	+username	+"\n"
				+ "Password: "	+password	+"\n";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}
}
