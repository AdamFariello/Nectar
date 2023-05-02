package databaseCode;
import java.sql.*;
import java.util.ArrayList;

public class DBTest {
	public static void main (String args[]) throws SQLException {
		/////////////////////////
		// ONLY A TESTING FILE //
		/////////////////////////
		
		//General variables
		String [] dbs = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
		String db = dbs[2];
		String table = "user";
		DBConnetion con = new DBConnetion();
		con.startConnection(db);		
		DBQuery test = new DBQuery(con);
		
		//Testing 1D insertion
		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add("lq");
		temp.add("lq");
		temp.add("lq");
		test.insertIntoTableWithOutPrimaryKey_ArrObj(table, temp);
		System.out.println(test.getFromTable_2DArrStr(table));
		
		//Testing 2D insertion
		ArrayList<ArrayList<Object>> tableInputs = new ArrayList<ArrayList<Object>>();
		temp = new ArrayList<Object>();
		temp.add("yq");
		temp.add("yq");
		temp.add("yq");
		tableInputs.add(temp);
		
		temp = new ArrayList<Object>();
		temp.add("qq");
		temp.add("qq");
		temp.add("qq");
		tableInputs.add(temp);
		
		test.insertIntoTableWithOutPrimaryKey_2DArrObj(table, tableInputs);
		System.out.println(test.getFromTable_2DArrStr(table));
	}
}
