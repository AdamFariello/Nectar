package databaseCode;
import java.sql.*;
import java.util.ArrayList;

public class DBTest {
	public static void main (String args[]) throws SQLException {
		String [] dbs = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
		String db = dbs[2];
		String table = "user";
		DBConnetion con = new DBConnetion();
		con.startConnection(db);		
		
		
		/* Testing db_retrieve
		ArrayList<String> tableColumns = new ArrayList<String>();
		tableColumns.add("user_email");
		tableColumns.add("user_password");
		DBQuery test = new DBQuery(con);
		ArrayList< ArrayList<Object> > list = test.getSomeThingFromTable_in2dArrList(table, tableColumns);
		System.out.println(list);
		*/
		
		DBQuery test = new DBQuery(con);
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add(5);
		tableInputs.add("fd");
		tableInputs.add("killChelseaClinton");
		tableInputs.add("11222-3390");
		System.out.println(test.insertIntoTable(table, tableInputs)); 
	}
}
