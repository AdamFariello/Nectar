package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseCode.*;

public class test {
	public static void main (String args[]) throws SQLException {
		databaseConnection dc = new databaseConnection();
		dc.Connection();
		databaseQueries dq = new databaseQueries(dc);
		
		String table = "user";
		ArrayList<String> tableInputs = new ArrayList<String>();
		tableInputs.add("KbToys@gmail.com");
		tableInputs.add("PrintYourOwnMoney");
		tableInputs.add("111-222-3390");
		
		dq.insertIntoTable_WithOutPrimaryKey(table, tableInputs);
	}
}
