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
		ArrayList<Object> tableInputs = new ArrayList<Object>();
		tableInputs.add("0");
		tableInputs.add("Garfield_LZanya@gmail.com");
		tableInputs.add("killThePresident");
		tableInputs.add("111-222-3333");
		
		dq.insertIntoTable_IncludingPrimaryKey(table, tableInputs);
	}
}
