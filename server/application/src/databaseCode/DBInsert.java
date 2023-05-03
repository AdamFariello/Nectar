package databaseCode;

import java.sql.*;
import java.text.*;
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
	//TODO: Merge 2d and 1d array methods by using instanceof to check for which 
	//		ArrayList size it is 
	//		(Probable will do after project is over...)
	//TODO: Add proper checks to cross reference methods
	
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
	
	
	public static boolean insertIntoTableAndCrossReferenceWeakTable
	(String strongTable1, Object strongTable1Input, 
	 String strongTable2, Object strongTable2Input,
	 String weakTable) {
		//THIS METHODS CAN ONLY BE DONE WITH INSERTS WITH A PRIMARY KEY
		ArrayList<Object> weakTableInputs = new ArrayList<Object>();
		weakTableInputs.add(strongTable1Input);
		weakTableInputs.add(strongTable2Input);
		return insertIntoTableWithPrimaryKey_ArrObj(weakTable, weakTableInputs);
	}
	public static boolean insertIntoTableAndCrossReferenceWeakTable
	(String strongTable1, Object strongTable1Input, 
	 String strongTable2, ArrayList<Object> strongTable2Inputs,
	 String weakTable) {
		//THIS METHODS CAN ONLY BE DONE WITH INSERTS WITH A PRIMARY KEY
		if (! insertIntoTableWithPrimaryKey_ArrObj(strongTable2, strongTable2Inputs))
			return false;
		
		ArrayList<Object> weakTableInputs = new ArrayList<Object>();
		weakTableInputs.add(strongTable1Input);
		weakTableInputs.add(strongTable2Inputs.get(0));
		return insertIntoTableWithPrimaryKey_ArrObj(weakTable, weakTableInputs);
	}
	public static boolean insertIntoTwoTablesAndCrossReferenceWeakTable
	(String strongTable1, ArrayList<Object> strongTable1Inputs, 
	 String strongTable2, ArrayList<Object> strongTable2Inputs,
	 String weakTable) {
		//THIS METHODS CAN ONLY BE DONE WITH INSERTS WITH A PRIMARY KEY
		if(! insertIntoTableWithPrimaryKey_ArrObj(strongTable1, strongTable1Inputs))
			return false;
		if(! insertIntoTableWithPrimaryKey_ArrObj(strongTable2, strongTable2Inputs))
			return false;
		
		ArrayList<Object> weakTableInputs = new ArrayList<Object>();
		weakTableInputs.add(strongTable1Inputs.get(0));
		weakTableInputs.add(strongTable2Inputs.get(0));
		return insertIntoTableWithPrimaryKey_ArrObj(weakTable, weakTableInputs);
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
					if (currRowDataType.contains("varchar")    	  
						&& tableInputObj instanceof String) {
						ps.setString(i, (String) tableInputObj);
					} else if (currRowDataType.equals("int")      
							   && tableInputObj instanceof Integer) {
						ps.setInt(i, (Integer) tableInputObj);
					} else if (currRowDataType.equals("double")   
							   && tableInputObj instanceof Double) {
						ps.setDouble(i, (Double) tableInputObj);
					} else if (currRowDataType.equals("date")) { 
						if (tableInputObj instanceof String) {
							//String Format: "MM/DD/YYYY"
							String date = (String) tableInputObj;
							java.util.Date javaDate = new java.util.Date(date);
							java.sql.Date mysqlDate = new java.sql.Date(javaDate.getTime());
							ps.setDate(i, mysqlDate);
						} else if (tableInputObj instanceof java.util.Date) {
							java.util.Date javaDate = (java.util.Date) tableInputObj;
							java.sql.Date mysqlDate = new java.sql.Date (javaDate.getTime());
							ps.setDate(i, mysqlDate);
						} else if (tableInputObj instanceof java.sql.Date) {
							java.sql.Date mysqlDate = (java.sql.Date) tableInputObj;
							ps.setDate(i, mysqlDate);
						}
					} else if (currRowDataType.contains("datetime") 
								|| (currRowDataType.contains("timestamp")) ){
						if (tableInputObj instanceof String) { 
							String date = (String) tableInputObj;
							//String format = "YYYY-MM-DD HH:MM:SS";
							String format = "yyyy-MM-dd HH:mm:ss";
							DateFormat dateFormat = new SimpleDateFormat(format);
							java.util.Date javaDate = dateFormat.parse(date);
							
							java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
							Timestamp timeStamp = new Timestamp(sqlDate.getTime());
							ps.setTimestamp(i, timeStamp); 	
						} else if (tableInputObj instanceof java.util.Date) { 
							java.util.Date javaDate = (java.util.Date) tableInputObj;
							java.sql.Timestamp sqlDate = new java.sql.Timestamp(javaDate.getTime());
							ps.setTimestamp(i, sqlDate); 	
						} else if (tableInputObj instanceof java.sql.Date) { 
							java.sql.Timestamp sqlDate = (java.sql.Timestamp) tableInputObj;
							ps.setTimestamp(i, sqlDate); 		
						} 
						
						//java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
						
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