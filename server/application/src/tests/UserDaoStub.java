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
	private HashMap<String, ArrayList<String>> wishlists;
	private ProductVO product1, product2, product3;
	
	public UserDaoStub() {
		userIDs = new String[10];
		usernames = new String[10];
		passwords = new String[10];
		productIDs = new String[10];
		usernames[0] = "john@gmail.com";
		userIDs[0] = "123";
		passwords[0] = "Pass";
		productIDs[0] = "234";
		productIDs[1] = "111";
		wishlists = new HashMap<String, ArrayList<String>>();
		ArrayList<String> johnWishlist = new ArrayList<String>();
		johnWishlist.add("111");
		wishlists.put("123", johnWishlist);
		product1 = new ProductVO("Apple iPhone 14, 128GB, Midnight - Unlocked (Renewed)", 
				"https://www.amazon.com/Apple-iPhone-14-128GB-Midnight/dp/B0BN72FYFG/ref=sr_1_3?crid=3K4987EKWRM8J&keywords=iphone&qid=1681969144&sprefix=iphone%2Caps%2C92&sr=8-3&th=1", 
				"Amazon");
		product2 = new ProductVO("Nintendo Switch Mario Choose One Bundle", 
				"https://www.amazon.com/Test-5-Nintendo-Switch/dp/B09YZXLYG4/ref=sr_1_2?keywords=nintendo%2Bswitch&qid=1681979362&sprefix=nin%2Caps%2C82&sr=8-2&th=1",
				"Amazon");
		
	}
	
	public HashMap<String, ArrayList<String>> getAllUserWishLists(){
    	//return a hashmap mapping user ids to a list of all the product ids they track
        return wishlists;
    }
    
    public ArrayList<String> getUserWishList(String userID){
    	//return a list of all the product ids a user tracks
    	return wishlists.get(userID);
    }
    
    public ProductVO getProductInfoByProductID(String productID) {
    	//return product url, sitename and make a product vo setting the public variables with what you got
    	if(productID.equals("111")) {
    		return product1;
    	}else if(productID.equals(234)) {
    		return product2;
    	}else if(productID.equals("134")) {
    		return product3;
    	}
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
    	for (int i = 0; i < usernames.length; i++) {
			if(userIDs[i].equals(userID)) {
				UserVO result = new UserVO(userIDs[i], usernames[i], passwords[i], "");
				return result;
			}
		}
        return null;
    }

    public String addProductToUserWishlist(String userID, ProductVO product){
    	//add new product to user's wishlist and return its productID
    	wishlists.get(userID).add("134");
    	productIDs[2] = "134";
    	product3 = product;
        return null;
    }
    
    public boolean addProductToUserWishlist(String userID, String productID) {
    	//add product found already in the database based on the given product id to user's wishlist and return success or failer
    	if(!wishlists.containsKey(userID)) {
    		return false;
    	}
    	for (int i = 0; i < productIDs.length; i++) {
			if(productIDs[i].equals(productID)) {
				wishlists.get(userID).add(productID);
				return true;
			}
		}
    	return false;
    }
    
    public boolean removeProductFromUserWishlist(String userID, String productID){
    	//remove product to user's wishlist and return if its successful or not
    	if(!wishlists.containsKey(userID)) {
    		return false;
    	}
    	for (int i = 0; i < productIDs.length; i++) {
			if(productIDs[i].equals(productID)) {
				wishlists.get(userID).remove(productID);
				return true;
			}
		}
    	return false;
    }
}
