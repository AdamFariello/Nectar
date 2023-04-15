package databaseCode;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("serial") 
class unequalArrayListLengths extends Exception{ 
	private static String error = "The ArrayList for %s "
					 			+ "and the amount of columns of %s "
					 			+ "are different lengths";
	private static String errorBorders = "\n" + "#".repeat(error.length() * 2);			
	
	public unequalArrayListLengths() {}
	public unequalArrayListLengths(String table) {
		super (String.format(error + errorBorders, table, table));
	}
}

public class databaseQueries {
	private databaseConnection databaseConnection;
	public databaseQueries (databaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}
	
	
	@SuppressWarnings("finally")
	public ResultSet getColumnsOfTable_InResultSet (String table) {		
		ResultSet rs = null;
		try { 			
			Connection connection = databaseConnection.getConnection();
			
			//Trying to accomplish:
			//SELECT `COLUMN_NAME` 
	        //FROM `INFORMATION_SCHEMA`.`COLUMNS` 
	        //WHERE `TABLE_SCHEMA`='server' AND `TABLE_NAME`='table';
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
	@SuppressWarnings("finally")
	public ArrayList<String> getColumnsOfTable_InStringArrayList(String table) {		
		//Try and catch still used since of mysql connection can go down
		ArrayList<String> describeTable = null;
		try {
			describeTable = new ArrayList<String>(); 
			ResultSet rs = getColumnsOfTable_InResultSet(table);
			while(rs.next() == true) {
				describeTable.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return describeTable;
		}
	}
	
	
	public void insertIntoTable_IncludingPrimaryKey(String table, ArrayList<String> tableInputs) {
		insertIntoTable(table, tableInputs, getColumnsOfTable_InStringArrayList(table));
	}
	public void insertIntoTable_WithOutPrimaryKey(String table, ArrayList<String> tableInputs) {
		ArrayList<String> tableColumns = getColumnsOfTable_InStringArrayList(table);
		tableColumns.remove(0);
		insertIntoTable(table, tableInputs, tableColumns);
	}
	private void insertIntoTable (String table, ArrayList<String> tableInputs, ArrayList<String> tableColumns) {
		int connectionStatus = 0;
		try { 
			if (tableInputs.size() != tableColumns.size()) {
				throw new unequalArrayListLengths(table); 
			}
			
			//Converting: "[{insert values}]" to "{insert values}" 
			String tableColumnsString = tableColumns.toString(); 
			tableColumnsString = tableColumnsString.replace('[', ' ');
			tableColumnsString = tableColumnsString.replace(']', ' ');
			
			//Creating "?, " * #
			//Converting: "?. " to "?"
			String insertValues = "?, ".repeat(tableColumns.size());
			insertValues = insertValues.substring(0, insertValues.length() - 2);
			
			//Making:
			//"INSERT INTO ({colums1}, {colums2}, {&c.}) Values (?, ?, ?)"
			String s = " INSERT INTO %s (%s) Values (%s)";
			String query = String.format(s, table, tableColumns, insertValues);			

			//Sending it to database
			Connection connection = databaseConnection.getConnection();
			PreparedStatement ps  = connection.prepareStatement(query);
			connectionStatus  = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//TODO: See if this should be removed
			if (connectionStatus > 0) {
				System.out.println("Success");
			} else {
				System.out.println("Failure");
			}
		}
	}

	
	public void writeToAdministration(String admin_username, String admin_password, String admin_host) {
		
	}
}
