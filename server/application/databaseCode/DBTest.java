package databaseCode;
import java.sql.*;
import java.util.ArrayList;

public class DBTest {
	public static void main (String args[]) throws SQLException {
		/////////////////////////
		// ONLY A TESTING FILE //
		/////////////////////////
		
		//General variables
		String [] dbs = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
		String db = dbs[2];
		String table = "user";
		DBConnetion con = new DBConnetion();
		con.startConnection(db);		
		DBQuery test = new DBQuery(con);
		
		/*
		//Testing db_retrieve
		ArrayList<String> tableColumns = new ArrayList<String>();
		tableColumns.add("user_email");
		tableColumns.add("user_password");
		DBQuery test = new DBQuery(con);
		ArrayList< ArrayList<String> > list = test.getSomeFromTable_2DArrStr(table, tableColumns);
		System.out.println(list);
		*/
		
		/*
		//Testing Insertions with primary key
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add(0);
		tableInputs.add("f2d");
		tableInputs.add("ki2llChelseaClinton");
		tableInputs.add("112222-3390");
		test.insertIntoTableWithPrimaryKey(table, tableInputs); 
		
		//Testing Insertions with primary key
		test = new DBQuery(con);
		tableInputs = new ArrayList<Object>();
		tableInputs.add("BernieSanders");
		tableInputs.add("Garfield_l'zanya");
		tableInputs.add("112222-3");
		test.insertIntoTableWithOutPrimaryKey(table, tableInputs); 
		*/
		
		//Testing describing tables
		/*
		ResultSet rs = test.describeTable();
		while (rs.next()) {
			try {
				for (int i = 1; true; i++) {
					System.out.println(rs.getString(i));
				}
			} catch (Exception e) {
				System.out.println("===================");
			}
		}
		*/
		
		//Testing new way of describing tables
		ArrayList<String> ex = test.getColumnsFromTable_ArrStr(table);
		System.out.println(ex);		
	}
}
