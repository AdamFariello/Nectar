package server.app;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {
    String [] dbs = {
        "nectarDB_administration", 
        "nectarDB_products", 
        "nectarDB_user"
    };

    //TODO: Return a hashmap of userIds mapped to a string array of the productIDs in their wishlist
    public HashMap<String, String[]> getAllUserWishLists(){
        return null;
    }

    //TODO: Adds a product ID with its url to a user's wishlist, If the product is not in database add it
    public void addProductToUserWishList(String userID, String productID, String productUrl){

    }

    //Get product ID based off matching url 
    public String getProductIDByUrl(String url){
        return null;
    }

    public ArrayList<String> getUserByUserID(String userID){
        return null;
    }

    public ArrayList<String> getUserByEmailAddress(String userID){
        return null;
    }

    //TODO: We can decide on more later on
}
