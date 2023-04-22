package databaseCode;

import java.sql.Connection;

public class DBQuery extends DBConnetion{
	protected static DBConnetion DBConnetion; 
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion DBConnetion) {
		this.DBConnetion = DBConnetion;
	}
}
