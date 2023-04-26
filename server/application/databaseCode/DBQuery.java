package databaseCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBQuery extends DBRetrieve{
	//protected static DBConnetion DBConnetion; 
	
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnection) {
		super.dbConnection = dbConnection;
	}
	
	public static ArrayList<String> convertResultsetToStringArray(ResultSet rs) {
		try {
			ArrayList<String> describeTable = new ArrayList<String>(); 
			while(rs.next() == true) {describeTable.add(rs.getString(1));}
			return describeTable;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
