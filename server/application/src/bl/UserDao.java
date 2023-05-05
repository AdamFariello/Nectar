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
    
    public ProductVO getProductInfoByProductID() {
    	//reeturn product url, sitename and make a product vo setting the public variables with what you got
    	return null;
    }

    public UserVO getUserByEmailAdress(String emailAddress){
    	//return user id, user email, user password and make a user vo by setting the public variables with what you got
        return null;
    }

    public String addProductToUserWishlist(String userID, ProductVO product){
    	//add product to user's wishlist and return if its successful or not 
    	DBConnetion con = new DBConnetion();
		con.startConnection(dbs[1]);		
		DBQuery test = new DBQuery(con);
		
		ArrayList<Object> inputs = new ArrayList<Object>();
	
		String table = "user";
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("user_id");
		ArrayList<String> wheres = new ArrayList<String>();
		wheres.add("user_id");
		ArrayList<Object> wheresValues
		test.getFromTable_2DArrStr(table, columns, wheres, wheresValues);
		
		
		
		
		
		test.insertIntoStrongTable_WithPrimaryKey_ArrObj();
		
		con.endConnection();
		return null;
    }
    
    public boolean removeProductFromUserWishlist(String userID, String productID){
    	//remove product to user's wishlist and return if its successful or not
        return false;
    }

    //TODO: We can decide on more later on
}
