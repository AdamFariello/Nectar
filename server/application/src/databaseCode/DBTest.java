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
		
		//Testing db_retrieve
		ArrayList<String> tableColumns = new ArrayList<String>();
		tableColumns.add("user_email");
		tableColumns.add("user_password");
		ArrayList< ArrayList<String> > list = test.getFromTable_2DArrStr(table, tableColumns);
		System.out.println(list);
		
		//Testing insertIntoTableWithOutPrimaryKey
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add(9);
		tableInputs.add("h");
		tableInputs.add("h");
		tableInputs.add("h");
		test.insertIntoTableWithPrimaryKey(table, tableInputs);
		System.out.println(test.getFromTable_2DArrStr(table));
	}
}
