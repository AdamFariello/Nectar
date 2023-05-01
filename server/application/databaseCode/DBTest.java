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
		
		
		/*
		//Testing new way of describing tables
		test.getColumnsOfTable_ArrStr(table);
		String format = "getColumnsOfTable_ArrStr: \t%s\n"
					  + "getColumnsOfTable_ArrObj: \t%s\n"
					  + "getDatatypesOfTable_ArrStr \t%s\n"
				  	  + "getDatatypesOfTable_ArrObj \t%s\n"
					  + "getColumnsAndDatatypeFromTable_2DArrObj \t%s\n"
					  + "getColumnsAndDatatypeFromTable_2DArrStr \t\n%s\n"
					  ;
		String s = String.format(
						format, 
						test.getColumnsOfTable_ArrStr(table),
						test.getColumnsOfTable_ArrObj(table),
						test.getDatatypesOfTable_ArrStr(table),
						test.getDatatypesOfTable_ArrObj(table),
						test.getColumnsAndDatatypeFromTable_2DArrObj(table),
						test.getColumnsAndDatatypeFromTable_2DArrStr(table)
					);
		System.out.println(s);		
		*/
		
		
		//Calling whole table
		/*
		System.out.println("=".repeat(40));
		System.out.println("test.getFromTable_2DArrStr(table)");
		System.out.println(test.getFromTable_2DArrStr(table));
		System.out.println("-".repeat(40));
		*/
		
		//Calling columns of a table
		/*
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("user_id");
		columns.add("user_email");
		System.out.println("~".repeat(75));
		System.out.println("test.getFromTable_2DArrStr(table, columns)");
		System.out.println(test.getFromTable_2DArrStr(table, columns));
		System.out.println("~".repeat(75));
		*/
		
		
		//Calling Where of a table
		/*
		ArrayList<String> wheres = new ArrayList<String>();
		wheres.add("user_email");
		ArrayList<String> wheresValues = new ArrayList<String>();
		wheresValues.add("a");
		System.out.println(test.getFromTable_2DArrStr(table, wheres, wheresValues));
		*/
		
		//Calling Columns and Wheres of a table
		/*
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("user_id");
		columns.add("user_password");
		ArrayList<String> wheres = new ArrayList<String>();
		wheres.add("user_email");
		ArrayList<String> wheresValues = new ArrayList<String>();
		wheresValues.add("a");
		System.out.println("=".repeat(10));
		System.out.println("test.getFromTable_2DArrStr(table, columns, wheres, wheresValues)");
		System.out.println(test.getFromTable_2DArrStr(table, columns, wheres, wheresValues));
		System.out.println("-".repeat(10));
		*/
	}
}