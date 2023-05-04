package databaseCode;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;

public class DBInsert extends DBRetrieve {			
	//Strong table insertions
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
	
	
	//Weak table insertions
	public static boolean insertIntoWeakTable_ArrObj
	(String weakTable, ArrayList<Object> strongTablePrimaryKeys) {
		return insertIntoTableWithPrimaryKey_ArrObj(weakTable, strongTablePrimaryKeys);
	}
	public static boolean insertIntoWeakTable_2DArrObj
	(ArrayList< ArrayList<Object> > strongTablePrimaryKeys, String weakTable) {
		return insertIntoTableWithPrimaryKey_2DArrObj(weakTable, strongTablePrimaryKeys);
	}
	public static boolean insertIntoWeakTableWithLastStrongTableInsertedInto_ArrObj
	(ArrayList<Object> strongTablePrimaryKeysExceptLastTable,
	 String lastStrongTableName, ArrayList<Object> lastStrongTableInputs, 		
	 String weakTable) {
		ArrayList<Object> weakTableInputs = strongTablePrimaryKeysExceptLastTable;
		weakTableInputs.add(weakTableInputs.get(0));
		
		Boolean bool = insertIntoTableWithPrimaryKey_ArrObj(
							lastStrongTableName, lastStrongTableInputs
					   );
		if (!bool) {return false;}
		return insertIntoTableWithPrimaryKey_ArrObj(weakTable, weakTableInputs);
	}
	public static boolean insertIntoWeakTableWithLastStrongTableInsertedInto_2DArrObj
	(ArrayList<Object> strongTablePrimaryKeysExceptLastTable,
	 String lastStrongTableName, ArrayList<ArrayList<Object>> lastStrongTableInputs, 		
	 String weakTable) {
		ArrayList<ArrayList<Object>> weakTableInputs = new ArrayList<ArrayList<Object>>();
		for (ArrayList<Object> lastStringTableInput: lastStrongTableInputs) {
			ArrayList<Object> temp = strongTablePrimaryKeysExceptLastTable;
			temp.add(lastStringTableInput.get(0));
			weakTableInputs.add(temp);
		}	
		Boolean bool = insertIntoTableWithPrimaryKey_2DArrObj(
							lastStrongTableName, lastStrongTableInputs
					   );
		if (!bool) {return false;}
		return insertIntoTableWithPrimaryKey_2DArrObj(weakTable, weakTableInputs);
	}
	
	
	//Insertion
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
							ps.setDate(i, (java.sql.Date) tableInputObj);
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
							java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
							Timestamp timeStamp = new Timestamp(sqlDate.getTime());
							ps.setTimestamp(i, timeStamp); 
						} else if (tableInputObj instanceof java.sql.Date) { 
							java.sql.Date sqlDate = (java.sql.Date) tableInputObj;
							Timestamp timeStamp = new Timestamp(sqlDate.getTime());
							ps.setTimestamp(i, timeStamp); 		
						} else if (tableInputObj instanceof Timestamp) { 
							ps.setTimestamp(i, (Timestamp) tableInputObj); 		
						} 
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