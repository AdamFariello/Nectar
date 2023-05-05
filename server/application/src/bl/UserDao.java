package bl;
import java.util.ArrayList;
import java.util.HashMap;

import databaseCode.DBConnetion;
import databaseCode.DBQuery;

public class UserDao {
    String [] dbs = {
        "nectarDB_administration", 
        "nectarDB_products", 
        "nectarDB_user"
    };

    public HashMap<String, ArrayList<String>> getAllUserWishLists(){
    	//return a hashmap mapping user ids to a list of all the product ids they track
        
    	
    	return null;
    }
    
    public ArrayList<String> getUserWishList(String userID){
    	//return a list of all the product ids a user tracks
    	return null;
    }
    
    public ProductVO getProductInfoByProductID() {
    	//reeturn product url, sitename and make a product vo setting the public variables with what you got
    	return null;
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
    	
    	ArrayList<ArrayList<String>> user = test.getFromTable_2DArrStr(table, columns, wheres, wheresValues);
    	
    	UserVo userVo = new UserVo(user.get());
    	return null;
    }

    public Boolean addProductToUserWishlist(int userID, ProductVO product){
    	//add product to user's wishlist and return if its successful or not 
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
			
			ArrayList<ArrayList<Object>> arr = test.getFromTable_2DArrObj(
				table, columns, wheres, wheresValues
			);
			int product_id = (Integer) arr.get(0).get(0);
			
			
			test.setCurrentServer(dbs[1]);
			String weakTable = "userWishList";
			ArrayList<Object> strongTablePrimaryKeys = new ArrayList<Object>();
			strongTablePrimaryKeys.add(userID); strongTablePrimaryKeys.add(product_id);
			bool = test.insertIntoWeakTable_ArrObj(weakTable, strongTablePrimaryKeys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		con.endConnection();
		return bool;
    }
    
    public boolean removeProductFromUserWishlist(int userID, String productID){
    	//remove product to user's wishlist and return if its successful or not
        return false;
    }
    
    /*
    public static void main () {
    }
    */

    //TODO: We can decide on more later on
}
