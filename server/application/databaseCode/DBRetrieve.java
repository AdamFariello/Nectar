package databaseCode;

import java.sql.*;
import java.util.ArrayList;


public class DBRetrieve {	
	protected static DBConnetion dbConnection = null;
	
	//public DBRetrieve(databaseCode.DBConnetion DBConnetion) {super(DBConnetion);}
	
	@SuppressWarnings("finally")
	public static ResultSet getColumnsOfTable_InResultSet (String table) {				
		try { 						
			Connection connection = DBConnetion.getConnection();
			
			//Values of each %s:
			//	1) Database
			//	2) Table
			String query = "SELECT `COLUMN_NAME` " 
						 + "FROM `INFORMATION_SCHEMA`.`COLUMNS` "
						 + "WHERE `TABLE_SCHEMA`= ? AND `TABLE_NAME`= ? ";
			PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
			ps.setString(1, dbConnection.getCurrentServer());
			ps.setString(2, table);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return null;
		}
	}
	public static ArrayList<String> getColumnsOfTable_InStringArrayList(String table) {		
		return convertResultsetToStringArray(getColumnsOfTable_InResultSet (table));
	}

	
	@SuppressWarnings("finally")
	public static ResultSet getDataTypeOfTable_InResultSet(String table) {
		try { 						
			//Values of each %s:
			//	1) Database
			//	2) Table
			String query = " SELECT DATA_TYPE "
						 + " FROM INFORMATION_SCHEMA.COLUMNS "
						 + " WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? "
						 ;
			PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
			ps.setString(1, dbConnection.getCurrentServer());
			ps.setString(2, table);
			return ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return null;
		}
	}
	public static ArrayList<String> getDataTypeOfTable_InStringArrayList(String table) {		
		return convertResultsetToStringArray(getDataTypeOfTable_InResultSet(table));
	}
	
	public static ArrayList<String> getAllInformationFromTable_inStringArray(String table) {
		ResultSet rs = getAllInformationFromTable_inResultSet(table);
		System.out.println("test2: " +rs);
		return convertResultsetToStringArray(rs);
	}
	@SuppressWarnings("finally")
	public static ResultSet getAllInformationFromTable_inResultSet(String table) {
		try { 						
			String query = " SELECT * FROM " + table;
			PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
			//ps.setString(1, table);
			ResultSet rs = ps.executeQuery();
			System.out.println("test1: " +rs);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		return null;
	}
	
	@SuppressWarnings("finally")
	public static ArrayList<String> convertResultsetToStringArray(ResultSet rs) {
		try {
			ArrayList<String> describeTable = new ArrayList<String>(); 
			while(rs.next() == true) {describeTable.add(rs.getString(1));}
			return describeTable;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return null;
		}
	}
}