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
		dq.insertIntoTable_IncludingPrimaryKey(table, tableInputs);
	}
}
