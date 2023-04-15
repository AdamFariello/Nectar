package test;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseCode.*;

public class test {
	public static void main (String args[]) throws SQLException {
		databaseConnection dc = new databaseConnection();
		dc.Connection();
		databaseQueries dq = new databaseQueries(dc);
		
		ResultSet rs = dq.describeTable("user");
		rs.next();
		for (int i = 1; true; i++) 
			System.out.println(rs.getString(i));
	}
}
