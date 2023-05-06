package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBRetrieve {
	//TODO: Add hash-table, macro, or whatever
	//		Just something to explain specific numbers used in method	
	//TODO: Include DBRetrieve in this class
	//TODO: Theorize if this class can, and should, use <E>
	//TODO: Include more checks
	
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
	
	
	//Calling from table using: 
	//	a) table name
	public static ArrayList<ArrayList<String>> getFromTable_2DArrStr
	(String table) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertEntireTable(getFromTable_RS(table));
	}
	public static ArrayList<ArrayList<Object>> getFromTable_2DArrObj
	(String table) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertEntireTable(getFromTable_RS(table));
	}
	public static ResultSet getFromTable_RS
	(String table) {
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
	
	
	//Calling from table using: 
	//	a) table name
	//	b) columns of the table 
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
			String queryColumns = columns.toString();
			queryColumns = queryColumns.replace("[", "");
			queryColumns = queryColumns.replace("]", "");
			String queryFormat  = "SELECT %s FROM %s";
			String query 		= String.format(queryFormat, queryColumns, table);
			return dbConnetion.getConnection().prepareStatement(query).executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Calling from table using: 
	//	a) table name
	//	b) where colums (columns to check for command
	//	c) where values
	public static ArrayList<ArrayList<String>> getFromTable_2DArrStr
	(String table, ArrayList<String> wheres, ArrayList<String> wheresValues) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertEntireTable(getFromTable_RS(table, wheres, wheresValues));
	}
	public static ArrayList<ArrayList<Object>> getFromTable_2DArrObj
	(String table, ArrayList<String> wheres, ArrayList<String> wheresValues) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertEntireTable(getFromTable_RS(table, wheres, wheresValues));
	}
	public static ResultSet getFromTable_RS 
	(String table, ArrayList<String> wheres, ArrayList<String> wheresValues) {
		try {
			if (wheres.size() != wheresValues.size()) {
				throw new errorUnequalArrayListLengths("wheres", "wheresValues");
			}
			String queryWheres = wheres.toString().replace(",", "= ? AND ");
			queryWheres = queryWheres.replace("[","");
			queryWheres = queryWheres.replace("]"," = ?");
			
			String queryFormat = "SELECT * FROM %s WHERE %s ";
			String query 	   = String.format(queryFormat, table, queryWheres);
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			for (int i = 1; !wheresValues.isEmpty(); i++) {
				ps.setString(i, wheresValues.remove(0));
			}
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//TODO: Fill in with actual stuff
		return null;
	}
	
	
	//Calling from table using: 
	//	a) table name
	//	b) columns of the table 
	//	c) where colums (columns to check for command
	//	d) where values
	public static ArrayList<ArrayList<String>> getFromTable_2DArrStr
	(String table, ArrayList<String> columns, 
	 ArrayList<String> wheres, ArrayList<String> wheresValues) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertEntireTable(getFromTable_RS(table, columns, wheres, wheresValues));
	}
	public static ArrayList<ArrayList<Object>> getFromTable_2DArrObj
	(String table, ArrayList<String> columns, 
	 ArrayList<String> wheres, ArrayList<String> wheresValues) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertEntireTable(getFromTable_RS(table, columns, wheres, wheresValues));
	}
	public static ResultSet getFromTable_RS
	(String table, ArrayList<String> columns, 
	 ArrayList<String> wheres, ArrayList<String> wheresValues) {
		try {		
			if (wheres.size() != wheresValues.size()) {
				throw new errorUnequalArrayListLengths("wheres", "wheresValues");
			}
			
			String queryColumns = columns.toString()
					 					 .substring(1, columns.toString().length()-1);
			
			String queryWheres = wheres.toString().replace(",", "= ? AND ");
			queryWheres = queryWheres.replace("[","");
			queryWheres = queryWheres.replace("]"," = ?");
			
			String queryFormat = "SELECT %s FROM %s WHERE %s ";
			String query 	   = String.format(
									queryFormat, 
									queryColumns, table, queryWheres
						 	   );
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			for (int i = 1; !wheresValues.isEmpty(); i++) {
				ps.setString(i, wheresValues.remove(0));
			}
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//Calling from table using: 
	//	a) table name
	//	b) columns of the table 
	//	e) group by
	public static ArrayList<ArrayList<String>> getFromTable_2DArrStr
	(String table, ArrayList<String> columns, String groupBy) {
		DBConversions<String> dbc = new DBConversions<String>();
		return dbc.convertEntireTable(
			getFromTable_RS(table, columns, groupBy)
		);
	}
	public static ArrayList<ArrayList<Object>> getFromTable_2DArrObj
	(String table, ArrayList<String> columns, 
	 ArrayList<String> wheres, ArrayList<String> wheresValues,
	 String groupBy) {
		DBConversions<Object> dbc = new DBConversions<Object>();
		return dbc.convertEntireTable(
			getFromTable_RS(table, columns, groupBy)
		);
	}
	public static ResultSet getFromTable_RS
	(String table, ArrayList<String> columns, String groupBy) {
		try {					
			String queryColumns = columns.toString()
					 					 .substring(1, columns.toString()
					 							 			  .length()-1);
			
			String queryFormat = "SELECT %s FROM %s GROUP BY %s";
			String query 	   = String.format(
									queryFormat, 
									queryColumns, table, groupBy
						 	   );
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}