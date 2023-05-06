package bl;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {
    String [] dbs = {
        "nectarDB_administration", 
        "nectarDB_products", 
        "nectarDB_user"
    };
    
    public void initializeDatabaseWithSampleUsers() {
    	//Add sample users with username and password here 
    }
    
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
    	//return user id, user email, user password and make a user vo by setting the public variables with what you got
        return null;
    }
    
    public UserVO getUserByUserID(String userID) {
    	//return user id, user email, user password and make a user vo by setting the public variables with what you got
        return null;
    }

    public String addProductToUserWishlist(String userID, ProductVO product){
    	//add new product to user's wishlist and return its productID
        return null;
    }
    
    public boolean addProductToUserWishlist(String userID, String productID) {
    	//add product found already in the database based on the given product id to user's wishlist and return success or failer
    	return false;
    }
    
    public boolean removeProductFromUserWishlist(String userID, String productID){
    	//remove product to user's wishlist and return if its successful or not
        return false;
    }
    
    public boolean addUser(UserVO user) {
    	//add user to database return true if its added successfully
    	return false;
    }

    //TODO: We can decide on more later on
}
