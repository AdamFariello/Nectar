package databaseCode;

import java.sql.*;
import java.util.ArrayList;


public class DBRetrieve extends DBQuery {	
	public DBRetrieve(databaseCode.DBConnetion DBConnetion) {
		super(DBConnetion);
	}
	
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
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, getCurrentServer());
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
			String query = "SELECT DATA_TYPE \n"
						 + "FROM INFORMATION_SCHEMA.COLUMNS \n"
						 + "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?"
						 ;
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, getCurrentServer());
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