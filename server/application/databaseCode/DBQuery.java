package databaseCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBQuery extends DBRetrieve{
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnetion) {
		super.dbConnetion = dbConnetion;
	}
}
