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
	//		(Possible not needed since I already check for incorrect lengths...)
	//TODO: Check if setDate() in insertIntoTable includes the time
	//TODO: Add string debugs 
	//		(unsure, maybe I'll include debug macro for compiling either 
	//		last minute, or after the project is done)
	
	public static boolean insertIntoTableWithPrimaryKey_ArrObj
	(String table, ArrayList<Object> tableInputs) {			
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
		temp.add(tableInputs);
		return insertIntoTableWithPrimaryKey_2DArrObj(table, temp);
	}
	public static boolean insertIntoTableWithPrimaryKey_2DArrObj
	(String table, ArrayList< ArrayList<Object> > tableInputs) {					
		ArrayList<String> columnsOfTable   = getColumnsOfTable_ArrStr(table);
		ArrayList<String> datatypesOfTable = getDatatypesOfTable_ArrStr(table);
		
		String queryColumns = columnsOfTable.toString()
											.replace("[", "")
											.replace("]", "");
		
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "";
		for(ArrayList<Object> ArrObj: tableInputs) {
			String temp = "?, ".repeat(ArrObj.size());
			temp = temp.substring(0, temp.length() - 2);
			queryInserts += String.format("(%s), ", temp);
		}
		queryInserts = queryInserts.substring(0, queryInserts.length() - 2);
						
		String query = String.format(
						"INSERT INTO %s (%s) VALUES %s", 
					   	table, queryColumns, queryInserts
					   );
		return insertIntoTable (query, tableInputs, columnsOfTable, datatypesOfTable);
	}
	
	public static boolean insertIntoTableWithOutPrimaryKey_ArrObj
	(String table, ArrayList<Object> tableInputs) {
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
		temp.add(tableInputs);
		return insertIntoTableWithOutPrimaryKey_2DArrObj(table, temp);
	}
	public static boolean insertIntoTableWithOutPrimaryKey_2DArrObj
	(String table, ArrayList<ArrayList<Object>> tableInputs) {
		ArrayList<String> columnsOfTable = getColumnsOfTable_ArrStr(table);
		columnsOfTable.remove(0);
		ArrayList<String> datatypesOfTable = getDatatypesOfTable_ArrStr(table);
		datatypesOfTable.remove(0);
		

		String queryColumns = columnsOfTable.toString()
											.replace("[", "")
											.replace("]", "");
		
		//Create "?" for each given argument, and remove final ", " in string
		String queryInserts = "";
		for(ArrayList<Object> ArrObj: tableInputs) {
			String temp = "?, ".repeat(ArrObj.size());
			temp = temp.substring(0, temp.length() - 2);
			queryInserts += String.format("(%s), ", temp);
		}
		queryInserts = queryInserts.substring(0, queryInserts.length() - 2);
		
		String query = String.format(
					 	"INSERT INTO %s (%s) VALUES %s", 
					 	table, queryColumns, queryInserts
					   );
		return insertIntoTable (query, tableInputs, columnsOfTable, datatypesOfTable);
	}
	
	private static boolean insertIntoTable 
	(String query, ArrayList<ArrayList<Object>> tableInputs, 
	 ArrayList<String> columnsOfTable, ArrayList<String> datatypesOfTable) {		
		try { 
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);		 
			
			for (int i = 1; !tableInputs.isEmpty();) {
				ArrayList<Object> ArrObj = tableInputs.remove(0);
				for (; !ArrObj.isEmpty(); i++) {
					//Either i - 1, or all else is i + 1
					//Object tableInputObj  = tableInputs.get(i - 1); 
					Object tableInputObj   = ArrObj.remove(0); 
					
					int size = columnsOfTable.size();
					String currRowCol	   = columnsOfTable.get((i - 1) % size);
					String currRowDataType = datatypesOfTable.get((i - 1) % size);
					
					//Can't be a switch since switches don't allow functions
					if (currRowDataType.contains("varchar")    	  && tableInputObj instanceof String) {
						ps.setString(i, (String) tableInputObj);
					} else if (currRowDataType.equals("int")      && tableInputObj instanceof Integer) {
						ps.setInt(i, (Integer) tableInputObj);
					} else if (currRowDataType.equals("double")   && tableInputObj instanceof Double) {
						ps.setDouble(i, (Double) tableInputObj);
					} else if (currRowDataType.equals("datetime") && 
							  (tableInputObj instanceof java.util.Date || tableInputObj instanceof java.sql.Date)) { 
						//Format: "MM/DD/YYYY"
						ps.setDate(i, (java.sql.Date) tableInputObj); 
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