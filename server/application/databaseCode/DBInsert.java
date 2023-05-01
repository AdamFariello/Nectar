package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBInsert extends DBRetrieve {			
	//TODO
	//	1) Check for incorrect inputs for columns
	//	2) java.sql.SQLException: Column count doesn't match value count at row 1
	//	   (Insert error catch to stop this)
	//	3) Account for no primary key when inserting into table
	
	public static boolean insertIntoTableWithPrimaryKey
	(String table, ArrayList<Object> tableInputs) {					
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "?, ".repeat(tableInputs.size());
		queryInserts 	    = queryInserts.substring(0, queryInserts.length() - 2);
		String queryformat = "INSERT INTO %s VALUES (%s)";
		String query 	   = String.format(queryformat, table, queryInserts);
		
		return insertIntoTable(
			query, tableInputs, 
			getColumnsOfTable_ArrStr(table), getDatatypesOfTable_ArrStr(table)
		);
	}
	public static boolean insertIntoTableWithOutPrimaryKey
	(String table, ArrayList<Object> tableInputs) {
		ArrayList<String> columnsOfTable = getColumnsOfTable_ArrStr(table);
		columnsOfTable.remove(0);
		ArrayList<String> datatypesOfTable = getDatatypesOfTable_ArrStr(table);
		datatypesOfTable.remove(0);
		
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "?, ".repeat(tableInputs.size()); 
		queryInserts 	    = queryInserts.substring(0, queryInserts.length()-2);
		String queryColumns = columnsOfTable.toString()
											.replace("[", "")
											.replace("]", "");
		String queryString  = "INSERT INTO %s (%s) VALUES %s";
		String query = String.format(queryString, table, queryInserts, queryColumns);
		
		return insertIntoTable (query, tableInputs, columnsOfTable, datatypesOfTable);
	}
	
	private static boolean insertIntoTable 
	(String query, ArrayList<Object> tableInputs, 
	 ArrayList<String> columnsOfTable, ArrayList<String> datatypesOfTable) {		
		try { 
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);		 
			
			for (int i = 1; !columnsOfTable.isEmpty(); i++) {
				//Either i - 1, or all else is i + 1
				Object tableInputObj = tableInputs.get(i - 1); 
				String rowCurCol	  = columnsOfTable.get(0);
				String rowCurDataType = datatypesOfTable.get(1);
				
				//Can't be a switch since switches don't allow functions
				if (rowCurDataType.equals("varchar")    	 && tableInputObj instanceof String) {
					ps.setString(i, (String) tableInputObj);
				} else if (rowCurDataType.equals("int")      && tableInputObj instanceof Integer) {
					ps.setInt(i, (Integer) tableInputObj);
				} else if (rowCurDataType.equals("double")   && tableInputObj instanceof Double) {
					ps.setDouble(i, (Double) tableInputObj);
				} else if (rowCurDataType.equals("datetime") && 
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
							rowCurCol, rowCurDataType
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