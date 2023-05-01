package server.app.databaseCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBQuery extends DBInsert {
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnetion) {
		super.dbConnetion = dbConnetion;
	}
}
