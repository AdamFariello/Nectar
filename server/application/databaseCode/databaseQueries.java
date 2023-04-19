package databaseCode;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("serial") 
class errorUnequalArrayListLengths extends Exception{ 
	private static String error = "The ArrayList for %s "
					 			+ "and the amount of columns of %s "
					 			+ "are different lengths";
	private static String errorBorder = "\n" + "=".repeat(error.length() * 2);			
	
	public errorUnequalArrayListLengths() {}
	public errorUnequalArrayListLengths(String table) {
		super (String.format(error + errorBorder, table, table));
	}
}
class errorUnequalObjectTypes extends Exception {
	//TODO: Create functions to catch bad datatypes 
	//For when inserting into the database
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
		//Values outside for testing
		int connectionStatus = 0;
		
		try { 
			if (tableInputs.size() != tableColumns.size()) {
				throw new errorUnequalArrayListLengths(table); 
			}
			
			//Converting: "[{insert values}]" to "{insert values}" 
			String tableColumnsString = tableColumns.toString(); 
			tableColumnsString = tableColumnsString.replace('[', ' ');
			tableColumnsString = tableColumnsString.replace(']', ' ');
			
			//Creating multiple "?" and removing the last generated comma
			String insertValues = "?, ".repeat(tableColumns.size());
			insertValues = insertValues.substring(0, insertValues.length() - 2);
			
			//Making:
			//"INSERT INTO ({colums1}, {colums2}, {&c.}) Values (?, ?, ?)"
			String s = " INSERT INTO %s (%s) Values (%s)";
			String query = String.format(s, table, tableColumnsString, insertValues);			
			
			//Sending it to database
			Connection connection = databaseConnection.getConnection();
			PreparedStatement ps  = connection.prepareStatement(query);
			
			
			
			//Replacing each "?" first introduced in the query string
			//You count from 1 for some reason.
			/*
			for (int i = 1; tableInputs.size() > 0; i++) {
				Object obj = tableInputs.remove(0);
				
				try {
					if (obj instanceof String) {
						ps.setString(i, (String) obj);
						
					} else if (obj instanceof Integer) {
						ps.setInt(i, (Integer) obj);
						
					} else {
						System.out.println("error");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
			*/
			//ps.setInt(1, Integer.parseInt(tableInputs.remove(0)));
			ps.setString(1, tableInputs.remove(0));
			ps.setString(2, tableInputs.remove(0));
			ps.setString(3, tableInputs.remove(0));
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

	/*
	 private void insertIntoTable (String table, ArrayList<String> tableInputs, ArrayList<String> tableColumns) {
		int connectionStatus = 0;
		try { 
			if (tableInputs.size() != tableColumns.size()) {
				throw new errorUnequalArrayListLengths(table); 
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
			
			//Replacing each "?" first introduced in the query string
			//You count from 1 for some reason.
			for (int i = 1; tableInputs.size() > 0; i++) {
				Object obj = tableInputs.remove(0);
				
				try {
					if (obj instanceof String) {
						ps.setString(i, (String) obj);
						
					} else if (obj instanceof Integer) {
						ps.setInt(i, (Integer) obj);
						
					} else {
						System.out.println("error");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
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
	*/
}
