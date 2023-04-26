package databaseCode;

import java.sql.Connection;

public class DBQuery extends DBRetrieve{
	//protected static DBConnetion DBConnetion; 
	
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnection) {
		super.dbConnection = dbConnection;
	}
	
	//TODO: place preparedstatement to arraylist string here
	
}
