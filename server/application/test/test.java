package test;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseCode.*;

public class test {
	public static void main (String args[]) throws SQLException {
		databaseConnection dc = new databaseConnection();
		dc.Connection();
		databaseQueries dq = new databaseQueries(dc);
		
		
		//dq.describeTable("user");
		ResultSet rs = dq.describeTable("user");
		while (rs.next() == true)
			System.out.println(rs.getString(1));
	}
}
