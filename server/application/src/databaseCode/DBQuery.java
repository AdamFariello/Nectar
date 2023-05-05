package databaseCode;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBQuery extends DBInsert {	
	@SuppressWarnings("static-access")
	public DBQuery(DBConnetion dbConnetion) {
		super.dbConnetion = dbConnetion;
	}
	
	public String setCurrentServer(String newdatabase) {
		return (dbConnetion.setCurrentServer(newdatabase));
	}

}
