package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseCode.*;

public class Main {
	public static void main (String args[]) throws SQLException {
		String [] dbs = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
		String table = dbs[2];

		ArrayList<Object> tableInputs = new ArrayList<Object>();
		//tableInputs.add("0");
		tableInputs.add("KbToys@gmail.com");
		tableInputs.add("PrintYourOwnMoney");
		tableInputs.add("111-222-3390");
		
		
		
	}
}
