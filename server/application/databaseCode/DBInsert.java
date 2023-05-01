package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBInsert extends DBRetrieve {			
	//TODO: Check for incorrect inputs for columns
	//TODO: java.sql.SQLException: Column count doesn't match value 
	//		count at row 1 
	//		(Insert error catch to stop this)
	//TODO: Account for no primary key when inserting into table
	//TODO: Check for error of using primary key method and not giving a primary key
	//		(Possible un-needed since I already check for incorrect lengths...)
	
	
	public static boolean insertIntoTableWithPrimaryKey
	(String table, ArrayList<Object> tableInputs) {					
		return insertIntoTableWithPrimaryKey(table, tableInputs);
	}
	public static boolean insertIntoTableWithPrimaryKey 
	(String table, ArrayList<ArrayList<Object>> tableInputs) {					
		ArrayList<String> columnsOfTable   = getColumnsOfTable_ArrStr(table);
		ArrayList<String> datatypesOfTable = getDatatypesOfTable_ArrStr(table);
		
		
		String queryColumns = columnsOfTable.toString()
											.replace("[", "")
											.replace("]", "");
		
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "?, ".repeat(tableInputs.size());
		queryInserts 	    = queryInserts.substring(0, queryInserts.length() - 2);
				
		String queryValues = "";
		for (ArrayList<Object> ArrObj: tableInputs) {
			queryValues += ArrObj.toString()
								 .replace("[", "(")
								 .replace("]", ")");
			queryValues += ", ";
		}
		queryValues = queryValues.substring(0, queryValues.length()-2);
		
		String queryFormat = "INSERT INTO %s (%s) VALUES %s";
		String query = String.format(queryFormat, table, queryColumns, queryInserts);
		
		return insertIntoTable (query, tableInputs, columnsOfTable, datatypesOfTable);
	}
	

	public static boolean insertIntoTableWithOutPrimaryKey
	(String table, ArrayList<Object> tableInputs) {
		ArrayList<String> columnsOfTable = getColumnsOfTable_ArrStr(table);
		columnsOfTable.remove(0);
		ArrayList<String> datatypesOfTable = getDatatypesOfTable_ArrStr(table);
		datatypesOfTable.remove(0);
		
		String queryColumns = columnsOfTable.toString()
											.replace("[", "")
											.replace("]", "");
		
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "?, ".repeat(tableInputs.size()); 
		queryInserts 	    = queryInserts.substring(0, queryInserts.length()-2);
		
		String queryFormat = "INSERT INTO %s (%s) VALUES (%s)";
		String query 	   = String.format(queryFormat, table, queryColumns, queryInserts);
		return insertIntoTable (query, tableInputs, columnsOfTable, datatypesOfTable);
	}
	public static boolean insertIntoTableWithOutPrimaryKey
	(String table, ArrayList<ArrayList<Object>> tableInputs) {
		ArrayList<String> columnsOfTable = getColumnsOfTable_ArrStr(table);
		columnsOfTable.remove(0);
		ArrayList<String> datatypesOfTable = getDatatypesOfTable_ArrStr(table);
		datatypesOfTable.remove(0);
		

		String queryColumns = columnsOfTable.toString()
											.replace("[", "")
											.replace("]", "");
		
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "?, ".repeat(tableInputs.size()); 
		queryInserts 	    = queryInserts.substring(0, queryInserts.length()-2);
		
		String queryValues = "";
		for (ArrayList<Object> ArrObj: tableInputs) {
			queryValues += ArrObj.toString()
								 .replace("[", "(")
								 .replace("]", ")");
			queryValues += ", ";
		}
		queryValues = queryValues.substring(0, queryValues.length()-2);
		
		String queryFormat = "INSERT INTO %s (%s) VALUES (%s)";
		String query 	   = String.format(queryFormat, table, queryColumns, queryInserts);
		return insertIntoTable (query, tableInputs, columnsOfTable, datatypesOfTable);
	}
	
	private static boolean insertIntoTable 
	(String query, ArrayList<Object> tableInputs, 
	 ArrayList<String> columnsOfTable, ArrayList<String> datatypesOfTable) {		
		try { 
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);		 
			
			for (int i = 1; !columnsOfTable.isEmpty(); i++) {
				//Either i - 1, or all else is i + 1
				//Object tableInputObj  = tableInputs.get(i - 1); 
				Object tableInputObj   = tableInputs.remove(0); 
				String currRowCol	   = columnsOfTable.remove(0);
				String currRowDataType = datatypesOfTable.remove(0);
				
				//Can't be a switch since switches don't allow functions
				if (currRowDataType.contains("varchar")    	  && tableInputObj instanceof String) {
					ps.setString(i, (String) tableInputObj);
				} else if (currRowDataType.equals("int")      && tableInputObj instanceof Integer) {
					ps.setInt(i, (Integer) tableInputObj);
				} else if (currRowDataType.equals("double")   && tableInputObj instanceof Double) {
					ps.setDouble(i, (Double) tableInputObj);
				} else if (currRowDataType.equals("datetime") && 
						  (tableInputObj instanceof java.util.Date || tableInputObj instanceof java.sql.Date)) { 
					//TODO: Check if it includes time
					ps.setDate(i, (java.sql.Date) tableInputObj); //Format: "MM/DD/YYYY"
				} else {
					String tableInputString = tableInputObj.toString(); 
					String tableInputDataTypeString; 
					try {
						tableInputDataTypeString = tableInputObj.getClass().getSimpleName();
					}	catch (NullPointerException e) {
						tableInputDataTypeString = "undefined";
					}
					
					throw new errorIncorrectDataTypeForTheTable(
							tableInputString, tableInputDataTypeString, 
							currRowCol, currRowDataType
					);
				}
			}
			
			if (ps.executeUpdate() > 0) {
				System.out.println("Success");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Failure");
		return false;
	}
}