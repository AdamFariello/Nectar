package databaseCode;

import java.sql.*;
import java.util.ArrayList;


public class DBRetrieve extends DBCommands {	
	public static ArrayList< ArrayList<Object> > getColumnsAndTypeOfTable_in2DArrList (String table) {
		return convertResultsetOfTableTo2DArrayList(getBothColumnsAndTypeOfTable_inRS(table));
	}
	public static ResultSet getBothColumnsAndTypeOfTable_inRS (String table) {
		//TODO: Create error to catch quotation marks missing when inserting into query string
		try { 						
			//Example: Getting all data types names from a table
			//	SELECT COLUMN_NAME, DATA_TYPE
			//	FROM information_schema.COLUMNS 
			//	WHERE table_schema='nectarDB_user' 
			//		and TABLE_NAME='user';
			String query = "SELECT COLUMN_NAME, DATA_TYPE " 
						 + "FROM INFORMATION_SCHEMA.COLUMNS "
						 + "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			ps.setString(1, dbConnetion.getCurrentServer());
			ps.setString(2, table);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public static ArrayList<Object> getColumnsOfTable_inArrList (String table) {
		return convertResultSetOfColumnToArrayList(getColumnsOfTable_inRS(table));
	}
	public static ResultSet getColumnsOfTable_inRS (String table) {
		//TODO: Create error to catch quotation marks missing when inserting into query string
		try { 						
			//Example: Getting all data types names from a table
			//	SELECT COLUMN_NAME, DATA_TYPE
			//	FROM information_schema.COLUMNS 
			//	WHERE table_schema='nectarDB_user' 
			//		and TABLE_NAME='user';
			String query = "SELECT COLUMN_NAME " 
						 + "FROM INFORMATION_SCHEMA.COLUMNS "
						 + "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			ps.setString(1, dbConnetion.getCurrentServer());
			ps.setString(2, table);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public static ArrayList<Object> getDataTypesOfTable_inArrList (String table) {
		return convertResultSetOfColumnToArrayList(getDataTypesOfTable_inRS(table));
	}
	public static ResultSet getDataTypesOfTable_inRS (String table) {
		//TODO: Create error to catch quotation marks missing when inserting into query string
		try { 						
			//Example: Getting all data types names from a table
			//	SELECT COLUMN_NAME, DATA_TYPE
			//	FROM information_schema.COLUMNS 
			//	WHERE table_schema='nectarDB_user' 
			//		and TABLE_NAME='user';
			String query = "SELECT DATA_TYPE " 
						 + "FROM INFORMATION_SCHEMA.COLUMNS "
						 + "WHERE TABLE_SCHEMA = ? AND TABLE_NAME= ?";
			PreparedStatement ps = dbConnetion.getConnection().prepareStatement(query);
			ps.setString(1, dbConnetion.getCurrentServer());
			ps.setString(2, table);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public static ArrayList< ArrayList<Object> > getEveryThingFromTable_in2dArrList (String table) {
		return convertResultsetOfTableTo2DArrayList(getEveryThingFromTable_inRS(table));
	}
	public static ResultSet getEveryThingFromTable_inRS (String table) {
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

	
	public static ArrayList< ArrayList<Object> > getSomeThingFromTable_in2dArrList (String table, ArrayList<String> columns) {
		return convertResultsetOfTableTo2DArrayList(getSomeThingFromTable_inRS(table, columns));
	}
	public static ResultSet getSomeThingFromTable_inRS (String table, ArrayList<String> columns) {
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
}