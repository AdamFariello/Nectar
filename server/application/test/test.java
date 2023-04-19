package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseCode.*;

public class test {
	public static void main (String args[]) throws SQLException {		
		databaseConnection dc = new databaseConnection();
		String table = "user";
		dc.startConnection(table);
		databaseQueries dq = new databaseQueries(dc);
		
		ArrayList<String> tableInputs = new ArrayList<String>();
		tableInputs.add("0");
		tableInputs.add("KbToys@gmail.com");
		tableInputs.add("PrintYourOwnMoney");
		tableInputs.add("111-222-3390");
		
		dq.insertIntoTable_WithPrimaryKey(table, tableInputs);
	}
}
