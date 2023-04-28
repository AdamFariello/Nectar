package databaseCode;

import java.sql.*;
import java.util.ArrayList;


public class DBRetrieve extends DBConversions {	
	//Columns and DataTypes
	public static ArrayList<Object> getColumnsFromTable_2DArrObj (String table) {
		return convertColumn_RStoArrObj(getColumnsFromTable_RS(table));
	}
	public static ArrayList<String> getColumnsFromTable_2DArrStr (String table) {
		return convertColumn_RStoArrStr(getColumnsFromTable_RS(table));
	}
	public static ResultSet getColumnsFromTable_RS (String table) {
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
	
	public static ArrayList<Object> getDatatypesOfTable_2DArrObj (String table) {
		return convertColumn_RStoArrObj(getDatatypesFromTable_RS(table));
	}
	public static ArrayList<String> getDatatypesOfTable_2DArrStr (String table) {
		return convertColumn_RStoArrStr(getDatatypesFromTable_RS(table));
	}
	public static ResultSet getDatatypesFromTable_RS (String table) {
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
		
	public static ArrayList< ArrayList<Object> > getColumnsAndDatatypeFromTable_2DArrObj (String table) {
		return convertTable_RSto2DArrObj(getColumnsAndDatatypeFromTable_RS(table));
	}
	public static ArrayList< ArrayList<String> > getColumnsAndDatatypeFromTable_2DArrStr (String table) {
		return convertTable_RSto2DArrStr(getColumnsAndDatatypeFromTable_RS(table));
	}
	public static ResultSet getColumnsAndDatatypeFromTable_RS (String table) {
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

	//TODO: Figure if there can be a better name, (probably not + unimportant).
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
}