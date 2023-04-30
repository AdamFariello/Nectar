package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBInsert extends DBRetrieve {	
	/*
	public static Boolean insertIntoTable(String table, ArrayList<Object> tableInputs) {
		try { 
			String query = "INSERT INTO " + table + " "
						 + "VALUES (?, ?, ?, ?) "
						 ;
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			ps.setInt   (1, (Integer) tableInputs.get(0));
			ps.setString(2, (String)  tableInputs.get(1));
			ps.setString(3, (String)  tableInputs.get(2));
			ps.setString(4, (String)  tableInputs.get(3));
			//DEBUG System.out.println(ps.toString());
			
			//Return success
			if (ps.executeUpdate() > 0) {
				//TODO figure if this should be commented out
				System.out.println("Success");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		System.out.println("Failure");
		return false;
	}
	*/
	
	/*
	public static void insertIntoTableWithOutPrimaryKey(String table, ArrayList<Object> tableInputs) {
		ArrayList<String> tableColumns = getColumnsOfTable_InResultSet(table);
		tableColumns.remove(0);
		insertIntoTable(table, tableInputs, tableColumns);
	}
	*/
	/*
	private static boolean insertIntoTable (String table, ArrayList<Object> tableInputs) {
		//TODO
		//	1) Check for incorrect inputs for columns
		//	2) Merge checks of inputs and datatype
		//	3) Account for no primary key when inserting into table
		
		try { 
			String temp = "?, ".repeat(tableInputs.size());
			temp = temp.substring(0, temp.length()-2);
			String query = "INSERT INTO " + table + " VALUES (" + temp + ")";
			
			ArrayList< ArrayList<Object> > columnsAndDatatypesOfTable = getColumnsAndTypeOfTable_in2DArrList(table);
			
			for (int i = 0; !columnsAndDatatypesOfTable.isEmpty(); i++) {
				Object obj = tableInputs.get(i);
				
				ArrayList<Object> currentRow = columnsAndDatatypesOfTable.remove(0);
				String rowCurCol	  = (String) currentRow.get(0);
				String rowCurDataType = (String) currentRow.get(1);
				
				
				//Can't be a switch since switches don't allow functions
				if (rowCurDataType.equals("varchar")    && obj instanceof String) {
					//TODO this
				} else if (rowCurDataType == "int"      && obj instanceof Integer) {
					//TODO this
				} else if (rowCurDataType == "varchar"  && obj instanceof Double) {
					//TODO this
				} else if (rowCurDataType == "datetime" && obj instanceof String) {
					//TODO Later
				} else if (rowCurDataType == "date"     && obj instanceof String) {
					//TODO Later
				} else {
					//Catching an error
					//TODO Later
					String toBeInsertedVariable = obj.toString(); 
					
					//Null string gives error
					String toBeInsertedDatatype; 
					try {
						toBeInsertedDatatype = obj.getClass().getSimpleName();
					}	catch (Exception e) {
						toBeInsertedDatatype = "undefined";
					}
					
					throw new errorIncorrectDataTypeForTheTable(
							toBeInsertedVariable, toBeInsertedDatatype, 
							rowCurCol, rowCurDataType
					);
				}
			}
			*/
			/*
			
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
			
			//Return success
			if (ps.executeUpdate() > 0) {
				//TODO figure if this should be commented out
				System.out.println("Success");
				return true;
			}
			
			*/
		/*} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Failure");
		return false;
	}*/
}


/*
	//TODO Figure out what to do with old code
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