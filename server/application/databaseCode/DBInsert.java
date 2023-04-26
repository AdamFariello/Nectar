package databaseCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DBInsert extends DBRetrieve{	
	public static void insertIntoTable_WithPrimaryKey(String table, ArrayList<Object> tableInputs) {
		insertIntoTable(table, tableInputs, getColumnsOfTable_InStringArrayList(table));
	}
	public static void insertIntoTable_WithOutPrimaryKey(String table, ArrayList<Object> tableInputs) {
		ArrayList<String> tableColumns = getColumnsOfTable_InStringArrayList(table);
		tableColumns.remove(0);
		insertIntoTable(table, tableInputs, tableColumns);
	}
	private static void insertIntoTable (String table, ArrayList<Object> tableInputs, ArrayList<String> tableColumns) {
		int connectionStatus = 0;
		try { 
			//TODO: Merge both of these checks
			
			//TODO: Account for no primary key and/or other columns not being used
			//Checking for equal amount of inputs being used to the table
			if (tableInputs.size() != tableColumns.size()) {
				throw new errorUnequalArrayListLengths(table); 
			}
			
			//Checking for correct datatypes
			for (int i = 0; !tableColumns.isEmpty(); i++) {
				String currentColumn = tableColumns.remove(0);
				Object obj = tableInputs.get(i);
				switch (currentColumn) {
					case "varchar": if (obj instanceof String)  {break;}
					case "int":     if (obj instanceof Integer) {break;}
					case "double":  if (obj instanceof Double)  {break;}
					//TODO: case "datetime": if (obj instanceof Date) {}
					//TODO: case "date": if (obj instanceof Date) {}
					default: 
						String toBeInsertedVariable = obj.toString(); 
						
						//Null string gives error
						String toBeInsertedDatatype; 
						try {
							toBeInsertedDatatype = obj.getClass().getSimpleName();
						}	catch (Exception e) {
							toBeInsertedDatatype = "undefined";
						}
						
						String desiredColumn = currentColumn; 
						String desiredColumnDatatype = 
						getDataTypeOfTable_InStringArrayList(table).get(i);
						
						throw new errorIncorrectDataTypeForTheTable(
								toBeInsertedVariable, toBeInsertedDatatype, 
								desiredColumn, desiredColumnDatatype
						);
				}
				
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
			Connection connection = getConnection();
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
	
	public String toString() {
		return "Inside DBQInsert \n";
	}
}

/*
	private static void insertIntoTable (String table, ArrayList<String> tableInputs, ArrayList<String> tableColumns) {
		//Values outside for testing
		int connectionStatus = 0;
		
		try { 
			if (tableInputs.size() != tableColumns.size()) {
				throw new errorUnequalArrayListLengths(table); 
			} else if (true) {
				
				throw new errorIncorrectDataTypeForTheTable();
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
			Connection connection = DBConnetion.startConnection();
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
			
			//ps.setInt(1, Integer.parseInt(tableInputs.remove(0)));
			ps.setInt(1, Integer.parseInt(tableInputs.remove(0)));
			ps.setString(2, tableInputs.remove(0));
			ps.setString(3, tableInputs.remove(0));
			ps.setString(4, tableInputs.remove(0));
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