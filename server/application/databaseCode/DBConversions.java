package databaseCode;

import java.sql.*;
import java.util.ArrayList;

public class DBConversions <E> {
	//TODO: Figure if scrap code should be removed
	
	public ArrayList<E> convertRow(ResultSet rs) {return convertRow(rs, 1);}	
	@SuppressWarnings("unchecked")
	public ArrayList<E> convertRow(ResultSet rs, int rowNumber) {
		try {
			for (int i = 0; i == rowNumber; i++) {rs.next();}
			
			ArrayList<E> rowOfTable = new ArrayList<E>();
			try {
				for (int i = 1; true; i++) {
					rowOfTable.add((E) rs.getObject(i));
				}
			} catch (Exception e) {
				return rowOfTable;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ArrayList<E>> convertRow
	(ResultSet rs, ArrayList<Integer> rowNumbers) {
		try {
			ArrayList<ArrayList<E>> rowsOfTable = new ArrayList<ArrayList<E>>();
			for (int i = 0; rs.next(); i++) {
				if (rowNumbers.contains(i)) {
					ArrayList<E> rowOfTable = new ArrayList<E>();
					try {
						for (int j = 1; true; j++) {
							rowOfTable.add((E) rs.getObject(j));
						}
					} catch (Exception e) {
						rowsOfTable.add(rowOfTable);
					}
				}
			}
			return rowsOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<E> convertColumn(ResultSet rs) {return convertColumn(rs, 1);}
	@SuppressWarnings("unchecked")
	public ArrayList<E> convertColumn(ResultSet rs, int columnNumber) {
		try {
			ArrayList<E> columnOfTable = new ArrayList<E>();
			while (rs.next()) {
				columnOfTable.add((E) rs.getObject(columnNumber));
			}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<E>> convertColumn
	(ResultSet rs, ArrayList<Integer> columnNumbers) {
		try {
			ArrayList<ArrayList<E>> columnsOfTable = new ArrayList<ArrayList<E>>();
			while (rs.next()) {
				ArrayList<E> columnOfTable = new ArrayList<E>();
				for (int i: columnNumbers) {
					columnOfTable.add((E) rs.getObject(i));
				}
				columnsOfTable.add(columnOfTable);
			}
			return columnsOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<E>> convertEntireTable(ResultSet rs) {		
		try {
			ArrayList<ArrayList<E>> columnOfTable = new ArrayList<ArrayList<E>>(); 
			while(rs.next() == true) {
				ArrayList<E> rowOfTable = new ArrayList<E>();
				try {
					for (int i = 1; true; i++) {
						rowOfTable.add((E) rs.getObject(i));
					}
				} catch (Exception e) {
					columnOfTable.add(rowOfTable);
				}
			}
			return columnOfTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

}