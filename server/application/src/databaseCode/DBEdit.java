package databaseCode;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBEdit <E> {
	//TODO 
	//	1) Figure out if this class should exist (will probable exist)
	//	2) Add functions to edit table entries 
	private DBConnetion DBConnetion = null;
	
	public DBEdit(DBConnetion DBConnetion) {this.DBConnetion = DBConnetion;}

	public Boolean delete (String table, ArrayList<E> columns, ArrayList<E> values) {
		try { 
			String wheres = columns.toString();
			wheres = wheres.toString()
						   .replace("[", "")
						   .replace("]", "")
						   .replace(",", " = ? AND")
					 ;
			wheres += " = ?";
			
			
			String query = String.format(
								"DELETE FROM %s WHERE %s", 
								table, wheres
							);
			PreparedStatement ps = DBConnetion.getConnection()
											  .prepareStatement(query);
			for (int i = 1; !values.isEmpty(); i++) {
				E e = values.remove(0);
				ps.setObject(i, e);
			}
			
			if (ps.executeUpdate() > 0) {
				System.out.println("Success");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
