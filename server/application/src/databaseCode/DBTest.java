package databaseCode;
import java.sql.*;
import java.util.ArrayList;

public class DBTest {
	private static String [] dbs = {
		"nectarDB_administration", 
		"nectarDB_products", 
		"nectarDB_user"
	};
	private static DBConnetion con = null;
	private static DBQuery test = null;
	
	private static ArrayList<Object> tableInputs = null;
	private static String table = null;
	
	public static void testInsertions_StrongTable () {
		/*
		//Testing 1D insertion
		String table = "user";
		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add("lq");
		temp.add("lq");
		temp.add("lq");
		test.insertIntoTableWithOutPrimaryKey_ArrObj(table, temp);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		//Testing 2D insertion
		String table = "user";
		ArrayList<ArrayList<Object>> tableInputs = new ArrayList<ArrayList<Object>>();
		temp = new ArrayList<Object>();
		temp.add("yq");
		temp.add("yq");
		temp.add("yq");
		tableInputs.add(temp);
		
		temp = new ArrayList<Object>();
		temp.add("qq");
		temp.add("qq");
		temp.add("qq");
		tableInputs.add(temp);
		
		test.insertIntoTableWithOutPrimaryKey_2DArrObj(table, tableInputs);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		//Testing cross reference inserts
		String table1 = "user";
		ArrayList<Object> user = new ArrayList<Object>();
		user.add(30);
		user.add("qwer");
		user.add("qwer");
		user.add("qwer");
		test.insertIntoTableWithPrimaryKey_ArrObj(table1, user);
		
		String table2 = "userPurchasingProfileDetails";
		ArrayList<Object> userProfile = new ArrayList<Object>();
		userProfile.add(2);
		userProfile.add("Joseph");
		
		java.util.Date date = new java.util.Date("05/02/2023");
		userProfile.add(date);
		
		userProfile.add("999999999999999");
		userProfile.add("111");
		userProfile.add("Discover");
		test.insertIntoTableWithPrimaryKey_ArrObj(table2, userProfile);
		
		String table = "amazonProductPriceHistoryDetails";
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0);
		list.add(16.00);
		list.add("2023-05-02 05:04:22");
		test.insertIntoTableWithPrimaryKey_ArrObj(table, list);
		System.out.println(test.getFromTable_2DArrStr(table)); 
		*/
	}
	public static void testInsertions_WeakTables () {
		//Testing weak tables (Meant to fail)
		/*
		String table = "userPurchasingProfile";
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add(0);
		tableInputs.add(0);
		test.insertIntoWeakTable_ArrObj(table,tableInputs);
		*/
		
		//Testing weak tables (Meant to succeed)
		table = "user";
		ArrayList<ArrayList<Object>> tableInputss = new ArrayList<ArrayList<Object>>();
		tableInputs = new ArrayList<Object>();
			tableInputs.add("k2toys@gmail.com");
			tableInputs.add("bernieSanders");
			tableInputs.add("121-1111-1311");
			tableInputss.add(tableInputs);
			
			tableInputs = new ArrayList<Object>();
			tableInputs.add("kbtoys@gmail.com");
			tableInputs.add("bernieSanders");
			tableInputs.add("111-1111-1311");
			tableInputss.add(tableInputs);
			
			tableInputs = new ArrayList<Object>();
			tableInputs.add("kgtoys@gmail.com");
			tableInputs.add("bernieSanders");
			tableInputs.add("211-1111-1311");
			tableInputss.add(tableInputs);
		test.insertIntoTableWithOutPrimaryKey_2DArrObj(table,tableInputss);
		
		
		table = "userPurchasingProfileDetails";
		tableInputss = new ArrayList<ArrayList<Object>>();
			tableInputs = new ArrayList<Object>();
			tableInputs.add("kb Joseph toys");
			tableInputs.add("10/20/3000");
			tableInputs.add("1111111111311");
			tableInputs.add("123");
			tableInputss.add(tableInputs);
			
			tableInputs = new ArrayList<Object>();
			tableInputs.add("kb Joseph toys");
			tableInputs.add("10/20/3000");
			tableInputs.add("2111111111311");
			tableInputs.add("123");
			tableInputss.add(tableInputs);
			
			tableInputs = new ArrayList<Object>();
			tableInputs.add("kb Joseph toys");
			tableInputs.add("10/20/3000");
			tableInputs.add("2111111111312");
			tableInputs.add("123");
			tableInputss.add(tableInputs);
		test.insertIntoTableWithOutPrimaryKey_2DArrObj(table,tableInputss);
		
		table = "userPurchasingProfile";
		tableInputss = new ArrayList<ArrayList<Object>>();
			for (int i = 1; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					tableInputs = new ArrayList<Object>();
					tableInputs.add(i);
					tableInputs.add(j);
					tableInputss.add(tableInputs);
				}
			}
		test.insertIntoWeakTable_2DArrObj(table,tableInputss);
	}
	
	public static void main (String args[]) throws SQLException {
		con = new DBConnetion();
		con.startConnection(dbs[2]);		
		test = new DBQuery(con);
		
		testInsertions_WeakTables();
	}
}
