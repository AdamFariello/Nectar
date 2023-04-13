package databaseCode;

import java.sql.*;
import databaseCode.databaseConnection;

public class databaseQueries {
	private databaseConnection databaseConnection;
	public databaseQueries (databaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}
	
	
	
	public void insertIntoDatabase(String table, String input) {
		try { 
			String query = " INSERT INTO " +table+ " ";			
			
			//PreparedStatement ps = connection.prepareStatement(query);
			
			//ps.executeUpdate();

			
			
			/*If eeding to test that the connection is sent
			int status = ps.executeUpdate();
			if (status > 0)
					System.out.println("Success");
			*/
			
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public ResultSet describeTable (String table) {
		/*
		switch (table) {
			case "administration": 
				return "(admin_id, admin_username, admin_password, admin_host) "
					 + "Values (?, ?, ?, ?) ";
				
			case "user":
				return "(user_id, user_email, user_password, user_telephone) "
					 + "Values (?,?,?,?) ";
				
			case "userPurchasingProfile":
				return user_id upp_typeOfCard upp_nameOnCard upp_dateOnCard upp_numberOnCard upp_securityCodeOnCard
				
			default:
				return "Invalid Table";
		}
		*/
		
		ResultSet rs = null;
		try { 			
			Connection connection = databaseConnection.Connection();
			
			/*
			 SELECT `COLUMN_NAME` 
	         FROM `INFORMATION_SCHEMA`.`COLUMNS` 
	         WHERE `TABLE_SCHEMA`='server' AND `TABLE_NAME`='table';
			*/
			String query = "SELECT `COLUMN_NAME` " 
						 + "FROM `INFORMATION_SCHEMA`.`COLUMNS` "
						 + "WHERE `TABLE_SCHEMA`='?' AND `TABLE_NAME`='?' ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, databaseConnection.getServerName());
			ps.setString(2, table);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			databaseConnection.closeConnection();
			return rs;
		}
	}

	
	public void writeToAdministration(String admin_username, String admin_password, String admin_host) {
		
	}
}
