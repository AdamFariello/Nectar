package bl;
import java.util.ArrayList;
import java.util.HashMap;

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

    public boolean addProductToUserWishlist(String userID, ProductVO product){
    	//add product to user's wishlist and return if its successful or not 
        return false;
    }
    
    public boolean removeProductToUserWishlist(String userID, String productID){
    	//remove product to user's wishlist and return if its successful or not
        return false;
    }

    //TODO: We can decide on more later on
}
