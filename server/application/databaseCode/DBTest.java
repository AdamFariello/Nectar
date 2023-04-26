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
		ArrayList<String> tableColumns = new ArrayList<String>();
		tableColumns.add("user_email");
		tableColumns.add("user_password");

		/*
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add("0");
		tableInputs.add("KbToys@gmail.com");
		tableInputs.add("PrintYourOwnMoney");
		tableInputs.add("111-222-3390");
		*/
		
		DBConnetion con = new DBConnetion();
		con.startConnection(db);		
		
		DBQuery test = new DBQuery(con);
		ArrayList< ArrayList<Object> > list = test.getSomeThingFromTable_in2dArrList(table, tableColumns);
		System.out.println(list);
	}
}
