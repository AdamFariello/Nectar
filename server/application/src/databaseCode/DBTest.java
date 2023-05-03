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
		DBConnetion con = new DBConnetion();
		con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
		
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
		*/
		
		//Testing cross reference inserts
		/*
		String table1 = "user";
		ArrayList<Object> user = new ArrayList<Object>();
		user.add(30);
		user.add("qwer");
		user.add("qwer");
		user.add("qwer");
		test.insertIntoTableWithPrimaryKey_ArrObj(table1, user);
		*/
		
		/*
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
		*/
		
		test.setCurrentServer(dbs[1]);
		String table = "amazonProductPriceHistoryDetails";
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0);
		list.add(16.00);
		list.add("2023-05-02 05:04:22");
		test.insertIntoTableWithPrimaryKey_ArrObj(table, list);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		/*
		String tableWeak = "userPurchasingProfile";
		System.out.println("");
		*/
	}
}
