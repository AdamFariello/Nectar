package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBRetrieve {
	//TODO: Add hash-table, macro, or whatever
	//		Just something to explain specific numbers used in method	
	//TODO: Include DBRetrieve in this class
	
	protected static DBConnetion dbConnetion = null;
	
	
	//Describing the table
	public static ArrayList<String> getColumnsOfTable_ArrStr(String table) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertColumn(getDescriptionOfTable_RS(table));
	}	
	public static ArrayList<Object> getColumnsOfTable_ArrObj(String table) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertColumn(getDescriptionOfTable_RS(table));
	}
	
	public static ArrayList<String> getDatatypesOfTable_ArrStr(String table) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertColumn(getDescriptionOfTable_RS(table), 2);
	}
	public static ArrayList<Object> getDatatypesOfTable_ArrObj(String table) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertColumn(getDescriptionOfTable_RS(table), 2);
	}
	
	public static ArrayList< ArrayList<String> > getColumnsAndDatatypeFromTable_2DArrStr (String table) {
		DBConversions<String> dbc = new DBConversions<String>();
		ArrayList<Integer> columns = new ArrayList<Integer>(); 
		columns.add(1); columns.add(2);
		return dbc.convertColumn(getDescriptionOfTable_RS(table), columns);
	}
	public static ArrayList< ArrayList<Object> > getColumnsAndDatatypeFromTable_2DArrObj (String table) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		ArrayList<Integer> columns = new ArrayList<Integer>(); 
		columns.add(1); columns.add(2);
		return dbc.convertColumn(getDescriptionOfTable_RS(table), columns);
	}

	public static ResultSet getDescriptionOfTable_RS (String table) {
		try {
			String queryFormat   = "Describe %s";
			String query 		 = String.format(queryFormat, table);
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	//Getting all contents from the table
	public static ArrayList<ArrayList<String>> getAllFromTable_2DArrStr(String table) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertEntireTable(getAllFromTable_RS(table));
	}
	public static ArrayList<ArrayList<Object>> getAllFromTable_2DArrObj(String table) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertEntireTable(getAllFromTable_RS(table));
	}
	public static ResultSet getAllFromTable_RS(String table) {
		try {			
			String queryFormat   = "SELECT * FROM %s";
			String query 		 = String.format(queryFormat, table);
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//Getting certain columns from the table
	public static ArrayList<ArrayList<String>> getFromTable_2DArrStr
	(String table, ArrayList<String> columns) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertEntireTable(getFromTable_RS(table, columns));
	}
	public static ArrayList<ArrayList<Object>> getFromTable_2DArrObj
	(String table, ArrayList<String> columns) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertEntireTable(getFromTable_RS(table, columns));
	}
	public static ResultSet getFromTable_RS
	(String table, ArrayList<String> columns) {
		try {			
			String queryInserts = "?, ".repeat(columns.size()); 
			queryInserts 	 	= queryInserts.substring(0, queryInserts.length()-2);
			
			String queryFormat   = "SELECT (%s) FROM %s";
			String query 		 = String.format(queryFormat, queryInserts, table);
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			for (int i = 1; !columns.isEmpty(); i++) {
				ps.setString(1, columns.get(0));
			}
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Getting certain entries from the table
	public static ResultSet getFromTable_RS
	(String table, ArrayList<String> columns, 
	 ArrayList<String> wheres, ArrayList<Object> wheresValues) {
		try {		
			if (wheres.size() != wheresValues.size()) {
				throw new errorUnequalArrayListLengths("wheres", "wheresValues");
			}
			
			String queryInserts = "?, ".repeat(columns.size()); 
			queryInserts 	 	= queryInserts.substring(0, queryInserts.length()-2);
			
			String queryWheres = "? = ? AND ".repeat(wheres.size());
			queryWheres 	   = queryWheres.substring(0, queryInserts.length()-5);
			
			String queryFormat = "SELECT (%s) FROM %s WHERE %s";
			String query 	   = String.format(
									queryFormat, 
									queryInserts, table, queryWheres
						 	   );
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			for (int i = 1; !columns.isEmpty(); i++) {
				ps.setString(1, columns.get(0));
			}
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}