package databaseCode;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;


public class DBRetrieve {
	//TODO: Add hash-table, macro, or whatever
	//		Just something to explain specific numbers used in method	
	//TODO: Include DBRetrieve in this class
	
	protected static DBConnetion dbConnetion = null;
	
	public static ArrayList<String> getColumnsOfTable_ArrStr (String table) {
		return convertColumn_RStoArrStr(getDescriptionOfTable_RS(table), 1);
	}	
	public static ArrayList<Object> getColumnsOfTable_ArrObj (String table) {
		return convertColumn_RStoArrObj(getDescriptionOfTable_RS(table), 1);
	}
	public static ArrayList<String> getDatatypesOfTable_ArrStr (String table) {
		return convertColumn_RStoArrStr(getDescriptionOfTable_RS(table), 2);
	}
	public static ArrayList<Object> getDatatypesOfTable_ArrObj (String table) {
		return convertColumn_RStoArrObj(getDescriptionOfTable_RS(table), 2);
	}
	
	public static ArrayList< ArrayList<String> > getColumnsAndDatatypeFromTable_2DArrStr (String table) {
		ArrayList<Integer> tempCol = new ArrayList<Integer>(); 
		tempCol.add(1); tempCol.add(2);
		return convertRows_RStoArrStr(getDescriptionOfTable_RS(table), tempCol);
	}
	public static ArrayList< ArrayList<Object> > getColumnsAndDatatypeFromTable_2DArrObj (String table) {
		ArrayList<Integer> tempCol = new ArrayList<Integer>(); 
		tempCol.add(1); tempCol.add(2);
		return convertRows_RStoArrObj(getDescriptionOfTable_RS(table), tempCol);
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
	
	
	/*
	//Getting contents from the table
	public static ArrayList< ArrayList<Object> > getAllFromTable_2DArrObj (String table) {
		return convertTable_RSto2DArrObj(getAllFromTable_RS(table));
	}
	public static ArrayList< ArrayList<String> > getAllFromTable_2DArrStr (String table) {
		return convertTable_RSto2DArrStr(getAllFromTable_RS(table));
	}
	public static ResultSet getAllFromTable_RS (String table) {
		try {
			//Example: Getting all content from a table
			//	SELECT * FROM user;
			
			//TODO Bring back:
			//String query = "SELECT * FROM ?";
			//PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			//ps.setString(1, table);
			
			String query = "SELECT * FROM " + table;
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static ArrayList< ArrayList<Object> > getSomeFromTable_2DArrObj (String table, ArrayList<String> columns) {
		return convertTable_RSto2DArrObj(getSomeFromTable_RS(table, columns));
	}
	public static ArrayList< ArrayList<String> > getSomeFromTable_2DArrStr (String table, ArrayList<String> columns) {
		return convertTable_RSto2DArrStr(getSomeFromTable_RS(table, columns));
	}
	public static ResultSet getSomeFromTable_RS (String table, ArrayList<String> columns) {
		try {
			//TODO Change to use string format
			String query = "SELECT "; 
			while (! columns.isEmpty()) {
				query += columns.remove(0);
				query += ", ";
			}
			
			//Removing the last comma in the query string
			query = query.substring(0, query.length() - 2) + " ";
			query += ("From " + table);
			
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
}