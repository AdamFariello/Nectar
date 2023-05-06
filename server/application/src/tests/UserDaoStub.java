package tests;

import java.util.ArrayList;
import java.util.HashMap;

import bl.ProductVO;
import bl.UserDao;
import bl.UserVO;

public class UserDaoStub extends UserDao{
	private String[] userIDs;
	private String[] usernames;
	private String[] passwords;
	private String[] productIDs;
	
	public UserDaoStub() {
		userIDs = new String[10];
		usernames = new String[10];
		passwords = new String[10];
		productIDs = new String[10];
		usernames[0] = "john@gmail.com";
		userIDs[0] = "123";
		passwords[0] = "Pass";
		productIDs[0] = "234";
		
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
		for (int i = 0; i < usernames.length; i++) {
			if(usernames[i].equals(emailAddress)) {
				UserVO result = new UserVO(userIDs[i], usernames[i], passwords[i], "");
				return result;
			}
		}
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
}
