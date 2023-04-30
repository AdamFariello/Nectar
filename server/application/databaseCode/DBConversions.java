package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBConversions {
	//TODO: figure out how to abstract this
	//TODO: Convert class to DBConverstion<E> [Doesn't need to be done]
	
	protected static DBConnetion dbConnetion = null;
	
	public static ArrayList<String> convertRow_RSToArrStr(ResultSet rs) {
		return convertRow_RSToArrStr(rs, 0);
	}
	public static ArrayList<Object> convertRow_RStoArrObj(ResultSet rs) {
		return convertRow_RStoArrObj(rs, 0);
	}
	public static ArrayList<String> convertRow_RSToArrStr(ResultSet rs, int rowNumber) {
		return convertArrObjtoArrStr(convertRow_RStoArrObj(rs, rowNumber));
	}
	public static ArrayList<Object> convertRow_RStoArrObj(ResultSet rs, int rowNumber) {
		try {
			for (int i = 0; i == rowNumber; i++) {rs.next();}
			
			ArrayList<Object> rowOfTable = new ArrayList<Object>();
			try {
				for (int i = 1; true; i++) {
					rowOfTable.add(rs.getObject(i));
				}
			} catch (Exception e) {
				return rowOfTable;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList< ArrayList<String> > convertRows_RStoArrStr
	(ResultSet rs, ArrayList<Integer> rowNumber) {
		ArrayList< ArrayList<String> > temp = new ArrayList< ArrayList<String> >();
		for (int i: rowNumber) {temp.add(convertRow_RSToArrStr(rs, i));}
		return temp;
	}
	public static ArrayList< ArrayList<Object> > convertRows_RStoArrObj
	(ResultSet rs, ArrayList<Integer> rowNumber) {
		ArrayList< ArrayList<Object> > temp = new ArrayList< ArrayList<Object> >();
		for (int i: rowNumber) {temp.add(convertRow_RStoArrObj(rs, i));}
		return temp;
	}

	public static ArrayList<String> convertColumn_RStoArrStr(ResultSet rs) {
		return convertColumn_RStoArrStr(rs, 0);
	}
	public static ArrayList<Object> convertColumn_RStoArrObj(ResultSet rs) {
		return convertColumn_RStoArrObj(rs, 0);
	}
	public static ArrayList<String> convertColumn_RStoArrStr(ResultSet rs, int columnNumber) {
		return convertArrObjtoArrStr(convertColumn_RStoArrObj(rs, columnNumber));
	}
	public static ArrayList<Object> convertColumn_RStoArrObj(ResultSet rs, int columnNumber) {
		try {
			ArrayList<Object> columnOfTable = new ArrayList<Object>();
			while (rs.next()) {columnOfTable.add(rs.getObject(columnNumber));}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList< ArrayList<String> > convertColumns_RStoArrStr
	(ResultSet rs, ArrayList<Integer> columnNumber) {
		ArrayList< ArrayList<String> > temp = new ArrayList< ArrayList<String> >();
		for (int i: columnNumber) {temp.add(convertColumn_RStoArrStr(rs, i));}
		return temp;
	}
	public static ArrayList< ArrayList<Object> > convertColumns_RStoArrObj
	(ResultSet rs, ArrayList<Integer> columnNumber) {
		ArrayList< ArrayList<Object> > temp = new ArrayList< ArrayList<Object> >();
		for (int i: columnNumber) {temp.add(convertColumn_RStoArrObj(rs, i));}
		return temp;
	}
	
	public static ArrayList<String> convertArrObjtoArrStr (ArrayList<Object> ArrObj) {
		ArrayList<String> temp = new ArrayList<String>();
		for (Object obj : ArrObj) {temp.add( (String) obj);}
		return temp;
	}
	
	
	public static ArrayList<String> convertPartOfRow_RStoArrStr
	(ResultSet rs, int rowNumber, ArrayList<Integer> columnNumber) {
		return convertArrObjtoArrStr(convertPartOfRow_RStoArrObj(rs, rowNumber, columnNumber));
	}
	public static ArrayList<Object> convertPartOfRow_RStoArrObj
	(ResultSet rs, int rowNumber, ArrayList<Integer> columnNumber) {
		try {
			//i = 0 since rs.next() normally is called before working with it
			for (int i = 0; i == rowNumber; i++) {rs.next();}

			ArrayList<Object> rowOfTable = new ArrayList<Object>();
			for (int i: columnNumber) { rowOfTable.add(rs.getObject(i));}
			return rowOfTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	public static ArrayList<String> convertPartOfColumn_RStoArrStr
	(ResultSet rs, ArrayList<Integer> rowNumber, int columnNumber) {
		return convertArrObjtoArrStr(convertPartOfColumn_RStoArrObj(rs, rowNumber, columnNumber));
	}
	public static ArrayList<Object> convertPartOfColumn_RStoArrObj
	(ResultSet rs, ArrayList<Integer> rowNumber, int columnNumber) {
		try {
			ArrayList<Object> columnOfTable = new ArrayList<Object>();
			for (int i = 0; rs.next(); i++) {
				if (rowNumber.contains(i)) {
					columnOfTable.add(rs.getObject(columnNumber));
				}
			}			
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
		
	
	public static ArrayList< ArrayList<String> > convertPartOfTable_RSto2DArrStr
	(ResultSet rs, ArrayList<Integer> rowNumber, ArrayList<Integer> columnNumber) {
		try {
			ArrayList< ArrayList<String> > columnOfTable = new ArrayList< ArrayList<String> >(); 
			int i = 0;
			while (rs.next()) {
				if (rowNumber.contains(i)) {
					ArrayList<String> rowOfTable = new ArrayList<String>();
					try {
						for (int j = 0; true; j++) {
							if (columnNumber.contains(j)) {
								rowOfTable.add(rs.getString(i));
							}
						}
					} catch (Exception e) {
						columnOfTable.add(rowOfTable);
					}
				}
				i++;
			}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	public static ArrayList< ArrayList<Object> > convertPartOfTable_RSto2DArrObj
	(ResultSet rs, ArrayList<Integer> rowNumber, ArrayList<Integer> columnNumber) {
		try {
			ArrayList< ArrayList<Object> > columnOfTable = new ArrayList< ArrayList<Object> >(); 
			int i = 0;
			while (rs.next()) {
				if (rowNumber.contains(i)) {
					ArrayList<Object> rowOfTable = new ArrayList<Object>();
					try {
						for (int j = 0; true; j++) {
							if (columnNumber.contains(j)) {
								rowOfTable.add(rs.getObject(i));
							}
						}
					} catch (Exception e) {
						columnOfTable.add(rowOfTable);
					}
				}
				i++;
			}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	public static ArrayList< ArrayList<String> > convertEntireTable_RSto2DArrStr(ResultSet rs) {
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
	public static ArrayList< ArrayList<Object> > convertEntireTable_RSto2DArrObj(ResultSet rs) {		
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
}