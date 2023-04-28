package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBInsert extends DBRetrieve {		
	/*
	//TODO re-implement this function
	public static void insertIntoTableWithOutPrimaryKey(String table, ArrayList<Object> tableInputs) {
		ArrayList<String> tableColumns = getColumnsOfTable_InResultSet(table);
		tableColumns.remove(0);
		insertIntoTable(table, tableInputs, tableColumns);
	}
	*/
	
	public static boolean insertIntoTable (String table, ArrayList<Object> tableInputs) {
		//TODO
		//	1) Check for incorrect inputs for columns
		//	2) Merge checks of inputs and datatype
		//	3) Account for no primary key when inserting into table
		
		try { 
			String temp = "?, ".repeat(tableInputs.size()); //Create "?" for each given argument
			temp = temp.substring(0, temp.length()-2);		//Remove final "." in string
			String query = "INSERT INTO " + table + " VALUES (" + temp + ")"; 
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			
			ArrayList< ArrayList<String> > columnsAndDatatypesOfTable = getColumnsAndDatatypeFromTable_2DArrStr(table);
			
			for (int i = 1; !columnsAndDatatypesOfTable.isEmpty(); i++) {
				//This has to be either i - 1, or everything else is i + 1
				Object tableInputObj = tableInputs.get(i - 1);
				
				ArrayList<String> currentRow = columnsAndDatatypesOfTable.remove(0);
				String rowCurCol	  = currentRow.get(0);
				String rowCurDataType = currentRow.get(1);
				
				//Can't be a switch since switches don't allow functions
				if (rowCurDataType.equals("varchar")    	 && tableInputObj instanceof String) {
					ps.setString(i, (String) tableInputObj);
				} else if (rowCurDataType.equals("int")      && tableInputObj instanceof Integer) {
					ps.setInt(i, (Integer) tableInputObj);
				} else if (rowCurDataType.equals("double")   && tableInputObj instanceof Double) {
					ps.setDouble(i, (Double) tableInputObj);
				} else if (rowCurDataType.equals("datetime") && tableInputObj instanceof java.util.Date) {
					//TODO: Check if it includes time
					//java.util.Date format: "MM/DD/YYYY"
					ps.setDate(i, (java.sql.Date) tableInputObj); 
				} else if (rowCurDataType.equals("datetime") && tableInputObj instanceof java.sql.Date) {
					//TODO: Check if it includes time
					//java.util.Date format: "MM/DD/YYYY"
					ps.setDate(i, (java.sql.Date) tableInputObj); 
				} else {
					//Catching an error
					String tableInputString = tableInputObj.toString(); 
					
					//Null string gives error
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
			
			//Return success
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