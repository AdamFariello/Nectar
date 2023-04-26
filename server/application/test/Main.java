package test;
import java.sql.*;
import java.util.ArrayList;
import databaseCode.*;

public class Main {
	public static void main (String args[]) throws SQLException {
		String [] dbs = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
		String db = dbs[2];

		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add("0");
		tableInputs.add("KbToys@gmail.com");
		tableInputs.add("PrintYourOwnMoney");
		tableInputs.add("111-222-3390");
		
		DBConnetion con = new DBConnetion();
		con.startConnection(db);		
		
		DBQuery test = new DBQuery(con);
		ArrayList<String> list = test.getAllInformationFromTable_inStringArray("user");
		System.out.println(list);
	}
}
