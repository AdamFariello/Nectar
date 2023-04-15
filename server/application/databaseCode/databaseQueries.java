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
						 + "WHERE `TABLE_SCHEMA`= ? AND `TABLE_NAME`= ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, databaseConnection.getServerName());
			ps.setString(2, table);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return rs;
		}
	}

	
	public void writeToAdministration(String admin_username, String admin_password, String admin_host) {
		
	}
}
