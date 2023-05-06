package bl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import databaseCode.*;

public class UserDao {
    String [] dbs = {
        "nectarDB_administration", 
        "nectarDB_products", 
        "nectarDB_user"
    };

    public HashMap<String, ArrayList<String>> getAllUserWishLists(){
    	//return a hashmap mapping user ids to a list of all the product ids they track
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
    	
		
		
    	return null;
    }
    
    public ArrayList<String> getUserWishList(String userID){
    	//return a list of all the product ids a user tracks
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
		
		String table = "userWishList"; 
		ArrayList<String> columns = new ArrayList<String>(); 
		columns.add("product_id");
		
		ArrayList<String> wheres = new ArrayList<String>();
		wheres.add("user_id");
		
		ArrayList<String> wheresValues = new ArrayList<String>();
		wheresValues.add(userID);
		
		
		DBConversions<String> DBConversion = new DBConversions<String>();
		ResultSet rs = test.getFromTable_RS(table, columns, wheres, wheresValues);
		return DBConversion.convertColumn(rs);	
	}
    
    public ProductVO getProductInfoByProductID(String productID) {
    	//What to do:
    	//	1) Return: product url, sitename 
    	//	2) Make a productVO, setting the public variables with what you got
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[1]);		
		DBQuery test = new DBQuery(con);
    	
    	String table = "product"; 
    	
    	//ArrayList<String> columns = new ArrayList<String>();
    	//columns.add("product_siteName"); columns.add("product_siteURL");
    	
    	ArrayList<String> wheres = new ArrayList<String>();
    	wheres.add("product_id");
    	
    	ArrayList<String> wheresValues = new ArrayList<String>();
    	wheresValues.add(productID);
    	
    	ArrayList<String> list = (test.getFromTable_2DArrStr(table, wheres, wheresValues)).get(0);
    	return new ProductVO (Integer.parseInt(productID), list.get(1), list.get(2));
    }

    public UserVO getUserByEmailAdress(String emailAddress){
    	//What to do:
    	//	1) Return: user id, user email, user password (using email address)
    	//	2) Make a user vo by setting the public variables with what you got
    	
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
    	
    	String table = "user"; 
    	
    	ArrayList<String> columns = new ArrayList<String>(); 
    	columns.add("user_id"); columns.add("user_email"); columns.add("user_password");
    	
    	ArrayList<String> wheres = new ArrayList<String>();
    	wheres.add("user_email");
    	
    	ArrayList<String> wheresValues = new ArrayList<String>();
    	wheresValues.add(emailAddress);
    	
    	ArrayList<String> user = (test.getFromTable_2DArrStr(table, columns, wheres, wheresValues)).get(0);
    	return new UserVO(user.get(0), user.get(1), user.get(2), user.get(3));
    }
    
    public Boolean addProductToUserWishlist(String userID, ProductVO product){
    	//add product to user's wish-list and return if its successful or not 
		DBConnetion con = new DBConnetion();
		Boolean bool = false;
		
    	try { 
			con.startConnection(dbs[1]);		
			DBQuery test = new DBQuery(con);
			
			String table = "product";
			ArrayList<String> columns = new ArrayList<String>();
			columns.add("product_id");
			
			ArrayList<String> wheres = new ArrayList<String>();
			wheres.add("product_siteName"); wheres.add("product_siteURL");
			
			ArrayList<String> wheresValues = new ArrayList<String>();
			String product_siteName = product.getEqualityComponents()[2];
			String product_siteUrl  = product.getEqualityComponents()[1];
			wheresValues.add(product_siteUrl); wheresValues.add(product_siteUrl);
			
			
			ArrayList<ArrayList<Object>> temp = test.getFromTable_2DArrObj(
				table, columns, wheres, wheresValues
			);
			int product_id = (Integer) temp.get(0).get(0);
			
			
			test.setCurrentServer(dbs[1]);
			String weakTable = "userWishList";
			ArrayList<Object> strongTablePrimaryKeys = new ArrayList<Object>();
			strongTablePrimaryKeys.add(Integer.parseInt(product_siteUrl)); 
			strongTablePrimaryKeys.add(product_id);
			bool = test.insertIntoWeakTable_ArrObj(weakTable, strongTablePrimaryKeys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		con.endConnection();
		return bool;
    }
    
    public boolean removeProductFromUserWishlist(int userID, String productID){
    	//remove product to user's wish-list and return if its successful or not
        return false;
    }
    
    /*
    public static void main () {
    }
    */

    //TODO: We can decide on more later on
}
