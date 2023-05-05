package databaseCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBQuery extends DBInsert {
	//TODO: Add function to process scripts (for team members)
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnetion) {
		super.dbConnetion = dbConnetion;
	}
	
	public String setCurrentServer(String newdatabase) {
		return (dbConnetion.setCurrentServer(newdatabase));
	}
}
