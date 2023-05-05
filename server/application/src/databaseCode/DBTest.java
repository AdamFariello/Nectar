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
	
	public static void success_InsertionsStrongTableWithPrimaryKeys () {
		String table;
		ArrayList<Object> list;
				
		//Testing cross reference inserts
		table = "user";
			list = new ArrayList<Object>();
			list.add(30);
			list.add("qwer");
			list.add("qwer");
			list.add("qwer");
		test.insertIntoStrongTable_WithPrimaryKey_ArrObj(table, list);
		System.out.println(test.getFromTable_2DArrStr(table)); 
		
		table = "userPurchasingProfileDetails";
			list = new ArrayList<Object>();
			list.add(2);
			list.add("Joseph");
		
			java.util.Date date = new java.util.Date("05/02/2023");
			list.add(date);
			
			list.add("999999999999999");
			list.add("111");
			list.add("Discover");
		test.insertIntoStrongTable_WithPrimaryKey_ArrObj(table, list);
		System.out.println(test.getFromTable_2DArrStr(table)); 
		
		table = "amazonProductPriceHistoryDetails";
			list = new ArrayList<Object>();
			list.add(0);
			list.add(16.00);
			list.add("2023-05-02 05:04:22");
		test.insertIntoStrongTable_WithPrimaryKey_ArrObj(table, list);
		System.out.println(test.getFromTable_2DArrStr(table)); 
	}
	public static void success_InsertionsStrongTableWithOutPrimaryKeys() {
		String table;
		ArrayList<Object> list;
		ArrayList<ArrayList<Object>> listList;
		
		//Testing 1D insertion
		table = "user";
			list = new ArrayList<Object>();
			list.add("lq");
			list.add("lq");
			list.add("lq");
		test.insertIntoStrongTable_WithOutPrimaryKey_ArrObj(table, list);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		//Testing 2D insertion
		table = "user";
			listList = new ArrayList<ArrayList<Object>>();
				list = new ArrayList<Object>();
				list.add("yq");
				list.add("yq");
				list.add("yq");
			listList.add(list);
		
				list = new ArrayList<Object>();
				list.add("qq");
				list.add("qq");
				list.add("qq");
			listList.add(list);
		test.insertIntoStrongTable_WithOutPrimaryKey_2DArrObj(table, listList);
		System.out.println(test.getFromTable_2DArrStr(table));
	}
	
	public static void fail_insertIntoWeakTable() {
		String table = "userPurchasingProfile";
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add(0);
		tableInputs.add(0);
		test.insertIntoWeakTable_ArrObj(table,tableInputs);
		//System.out.println(test.getFromTable_2DArrStr(table));
	}
	public static void success_insertIntoWeakTable() {		
		String table;
		ArrayList<Object> list;
		ArrayList<ArrayList<Object>> listlist;
		
		table = "user";
			listlist = new ArrayList<ArrayList<Object>>();
			list = new ArrayList<Object>();
			list.add("k2toys@gmail.com");
			list.add("bernieSanders");
			list.add("121-1111-1311");
				
			listlist.add(list);
			list = new ArrayList<Object>();
			list.add("kbtoys@gmail.com");
			list.add("bernieSanders");
			list.add("111-1111-1311");
				
			listlist.add(list);
			list = new ArrayList<Object>();
			list.add("kgtoys@gmail.com");
			list.add("bernieSanders");
			list.add("211-1111-1311");
			listlist.add(list);
		test.insertIntoStrongTable_WithOutPrimaryKey_2DArrObj(table,listlist);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		table = "userPurchasingProfileDetails";
			listlist = new ArrayList<ArrayList<Object>>();
			list = new ArrayList<Object>();
			list.add("kb Joseph toys");
			list.add("10/20/3000");
			list.add("1111111111311");
			list.add("123");
			listlist.add(list);
			
			list = new ArrayList<Object>();
			list.add("kb Joseph toys");
			list.add("10/20/3000");
			list.add("2111111111311");
			list.add("123");
			listlist.add(list);
			
			list = new ArrayList<Object>();
			list.add("kb Joseph toys");
			list.add("10/20/3000");
			list.add("2111111111312");
			list.add("123");
			listlist.add(list);
		test.insertIntoStrongTable_WithOutPrimaryKey_2DArrObj(table,listlist);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		table = "userPurchasingProfile";
		listlist = new ArrayList<ArrayList<Object>>();
			for (int i = 1; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					list = new ArrayList<Object>();
					list.add(i);
					list.add(j);
					listlist.add(list);
				}
			}
		test.insertIntoWeakTable_2DArrObj(table,listlist);
		System.out.println(test.getFromTable_2DArrStr(table));
	}
	
	public static void success_insertIntoWeakTable_AndStrongTables_WithPrimaryKey() {
		ArrayList<Object> list = new ArrayList<Object>();
		ArrayList<String> strongTableNames = new ArrayList<String>();
		ArrayList<ArrayList<Object>> strongMultiTableInputs = new ArrayList<ArrayList<Object>>();
		
		strongTableNames.add("user");
		list = new ArrayList<Object>();
		list.add(1);
		list.add("k2toys@gmail.com");
		list.add("bernieSanders");
		list.add("121-1111-1311");
		strongMultiTableInputs.add(list);
		
		strongTableNames.add("userPurchasingProfileDetails");
		list = new ArrayList<Object>();
		list.add(1);
		list.add("kb Joseph toys");
		list.add("10/20/3000");
		list.add("1111111111311");
		list.add("123");
		strongMultiTableInputs.add(list);
		
		test.insertIntoWeakTable_AndStrongTables_WithPrimaryKey(
			"userPurchasingProfile", strongTableNames, strongMultiTableInputs
		);
		System.out.println(test.getFromTable_2DArrStr("userPurchasingProfile"));
	}
	public static void success_insertIntoWeakTable_AndFirstStrongTable_WithPrimaryKey() {
		//Strong table 1: user                         
		//Strong table 2: userPurchasingProfileDetails
		//Weak table: userPurchasingProfile
		String weakTable = "userPurchasingProfile"; 
		ArrayList<Object> strongTablePrimaryKeysExceptFirstTable = new ArrayList<Object>(); 
		strongTablePrimaryKeysExceptFirstTable.add(2);
		
		String firstStringTableName = "user";
		ArrayList<Object> firstStrongTableInputs = new ArrayList<Object>();
		firstStrongTableInputs.add(2);
		firstStrongTableInputs.add("kb_toys@gmail.com");
		firstStrongTableInputs.add("password");
		firstStrongTableInputs.add("123-456-7896");
		
		test.insertIntoWeakTable_AndFirstStrongTable_WithPrimaryKey(
			weakTable, strongTablePrimaryKeysExceptFirstTable,
			firstStringTableName, firstStrongTableInputs
		);
		System.out.println(test.getFromTable_2DArrStr(weakTable));
	}
	protected static Boolean insertIntoWeakTable_AndLastStrongTable_WithPrimaryKey() {
		try {
			//Strong table 1: user                         
			//Strong table 2: userPurchasingProfileDetails
			//Weak table: userPurchasingProfile        
			String weakTable = "userPurchasingProfile";
			
			ArrayList<Object> strongTablePrimaryKeysExceptLastTable = new ArrayList<Object>();
			strongTablePrimaryKeysExceptLastTable.add(1);
			
			String lastStrongTableName = "userPurchasingProfileDetails"; 
			ArrayList<Object> lastStrongTableInputs = new ArrayList<Object>();
			lastStrongTableInputs.add(2);
			lastStrongTableInputs.add("Kg toys");
			lastStrongTableInputs.add("10/20/3000");
			lastStrongTableInputs.add("2111111111311");
			lastStrongTableInputs.add("123");
			
			test.insertIntoWeakTable_AndLastStrongTable_WithPrimaryKey(
				weakTable, strongTablePrimaryKeysExceptLastTable,
				lastStrongTableName, lastStrongTableInputs
			);
			System.out.println(test.getFromTable_2DArrStr(weakTable));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected static boolean WorkingDirectory() {
		try {
			final String workingDir = System.getProperty("user.dir");
			System.out.println("Working Directory = " + workingDir);
			return true;
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main (String args[]) throws SQLException {
		con = new DBConnetion();
		con.startConnection(dbs[2]);		
		test = new DBQuery(con);
	
		System.out.println(test.initServer());
	}

}