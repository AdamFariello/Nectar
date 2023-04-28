package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBConversions {
	//TODO figure out how to abstract this
	//AKA: replace "<object>" and "<string>" to "<E>"
	protected static DBConnetion dbConnetion = null;
	
	
	public static ArrayList<Object> convertRow_RSToArrObj(ResultSet rs) {
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
	
	
	public static ArrayList<Object> convertColumn_1DRStoArrObj(ResultSet rs) {
		return convertColumn_2DRStoArrObj(rs, 0);
	}
	public static ArrayList<String> convertColumn_1DRStoArrStr(ResultSet rs) {
		return convertColumn_2DRStoArrStr(rs, 0);
	}
	public static ArrayList<String> convertColumn_2DRStoArrStr(ResultSet rs, int columnNumber) {
		ArrayList<String> temp = new ArrayList<String>();
		for (Object obj : convertColumn_2DRStoArrObj(rs, columnNumber)) {temp.add( (String) obj);}
		return temp;
	}
	public static ArrayList<Object> convertColumn_2DRStoArrObj(ResultSet rs, int columnNumber) {
		try {
			ArrayList<Object> columnOfTable = new ArrayList<Object>();
			while (rs.next()) {columnOfTable.add(rs.getObject(columnNumber));}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList< ArrayList<Object> > convertTable_RSto2DArrObj(ResultSet rs) {		
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