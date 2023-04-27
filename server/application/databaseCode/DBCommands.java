package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBCommands {
	protected static DBConnetion dbConnetion = null;
	
	public static ArrayList<Object> convertResultSetOfRowToArrayList(ResultSet rs) {
		try {
			rs.next();
			ArrayList<Object> rowOfTable = new ArrayList<Object>();
			try {
				for (int i = 1; true; i++) {rowOfTable.add(rs.getObject(i));}
			} catch (Exception e) {
				return rowOfTable;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<String> convertRow_RSToArrStr(ResultSet rs) {
		try {
			rs.next();
			ArrayList<String> rowOfTable = new ArrayList<String>();
			try {
				for (int i = 1; true; i++) {rowOfTable.add(rs.getString(i));}
			} catch (Exception e) {
				return rowOfTable;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<Object> convertResultSetOfColumnToArrayList(ResultSet rs) {
		try {
			ArrayList<Object> columnOfTable = new ArrayList<Object>();
			while (rs.next()) {columnOfTable.add(rs.getObject(1));}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<String> convertColumn_RStoArrStr(ResultSet rs) {
		try { //convertTable_RSto2DArrStr
			ArrayList<String> columnOfTable = new ArrayList<String>();
			while (rs.next()) {columnOfTable.add(rs.getString(1));}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public static ArrayList< ArrayList<Object> > convertResultsetOfTableTo2DArrayList(ResultSet rs) {		
		try {
			ArrayList< ArrayList<Object> > columnOfTable = new ArrayList< ArrayList<Object> >(); 
			while(rs.next() == true) {
				ArrayList<Object> rowOfTable = new ArrayList<Object>();
				try {
					for (int i = 1; true; i++) {rowOfTable.add(rs.getObject(i));}
				} catch (Exception e) {
					columnOfTable.add(rowOfTable);
				}
			}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	public static ArrayList< ArrayList<String> > convertTable_RSto2DArrStr (ResultSet rs) {		
		try {
			ArrayList< ArrayList<String> > columnOfTable = new ArrayList< ArrayList<String> >(); 
			while(rs.next() == true) {
				ArrayList<String> rowOfTable = new ArrayList<String>();
				try {
					for (int i = 1; true; i++) {rowOfTable.add(rs.getString(i));}
				} catch (Exception e) {
					columnOfTable.add(rowOfTable);
				}
			}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
