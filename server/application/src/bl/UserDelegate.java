package bl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.*;

import databaseCode.DBConnetion;
import databaseCode.DBQuery;
import databaseCode.DBRetrieve;
import server.JSONMessage;


public class UserDelegate {
    private ProductTracker productTracker;
    private UserDao userDao;

    public UserDelegate(ProductTracker productTracker, UserDao userDao){
        this.productTracker = productTracker;
        this.userDao = userDao;
    }
    
    public void initializeTracker() {
    	HashMap<String, ArrayList<String>> wishlists = userDao.getAllUserWishLists();
    	for (Entry<String, ArrayList<String>> entry : wishlists.entrySet()) {
            String userID = entry.getKey();
            ArrayList<String> wishlist = entry.getValue();
            UserVO user = userDao.getUserByUserID(userID);
            productTracker.addUserWishList(user, wishlist.toArray(new String[wishlist.size()]));
        }
    }

    public JSONMessage handleGetProductData(JSONObject data){
    	String productData = productTracker.getProductData((String)data.get("ProductID")).encode();
        return new JSONMessage("ProductData", productData);
    }
    
    private JSONMessage handleLoginRequest(JSONObject data) {
    	try {
    		String username = data.get("Username").toString();
    		String password = data.get("Password").toString();
    		JSONObject result = new JSONObject();
    		UserVO user = userDao.getUserByEmailAdress(username);
    		if(user != null) {
    			if(password.equals(user.userPassword)) {
    				result.put("result", true);
            		result.put("UserID", 1);
    			}else {
    				result.put("result", false);
    			}
    			return new JSONMessage("Login Result", result.toString());
    		}else {
    			JSONObject obj = new JSONObject();
        		obj.put("Error Message", "Invalid Username");
    			return new JSONMessage("Login Error", obj.toString());
    		}
    	}catch (Exception e) {
    		JSONObject obj = new JSONObject();
    		obj.put("Error Message", "Invalid Login Request Data");
			return new JSONMessage("Login Error", obj.toString());
    	}	
    }
    
    private JSONMessage handleAddProductRequest(JSONObject data) {
    	try{
    		String userID = data.get("UserID").toString();
        	String productID = data.get("ProductID").toString();
        	String url = data.getString("Url").toString();
    		String website = data.getString("Website").toString();
    		boolean success = true;
        	if(productID.isEmpty()) {      		
        		ProductVO productVO = new ProductVO();
            	productVO.url = data.getString("Url").toString();
            	productVO.siteName = website;
            	productVO.title = productTracker.getProductDataFromUrl(url, website).title;
            	
            	productID = userDao.addProductToUserWishlist(userID, productVO);
        	}else {
        		success = userDao.addProductToUserWishlist(userID, productID);
        	}
        	if(success) {
        		UserVO user = userDao.getUserByUserID(userID);
        		success = productTracker.addUser(user, productID, url, website);
        	}	
        	JSONObject result = new JSONObject();
    		result.put("result", success);
        	return new JSONMessage("Add Product Result", result.toString());
    	}catch (Exception e) {
    		JSONObject obj = new JSONObject();
    		obj.put("Error Message", "Invalid Add Product Request Data");
			return new JSONMessage("Add Product Error", obj.toString());
    	}	
    }
    
    private JSONMessage handleRemoveProductRequest(JSONObject data) {
    	String userID = data.get("UserID").toString();
    	String productID = data.get("ProductID").toString();
    	boolean success = productTracker.removeUser(userID, productID);
    	success = userDao.removeProductFromUserWishlist(userID, productID);
    	JSONObject result = new JSONObject();
    	result.put("result", success);
    	return new JSONMessage("Remove Product Result", result.toString());
    }
    
    private JSONMessage handleGetUserWishlistRequest(JSONObject data) {
    	String userID = data.get("UserID").toString();
    	JSONObject result = new JSONObject();
    	
    	/*ArrayList<String> wishlist = userDao.getUserWishList(userID);
    	for (String productID: wishlist) {
    		JSONObject productInfo = new JSONObject();
        	productInfo = productTracker.getProductData(productID);
    	}*/
    	/*JSONArray array = new JSONArray();
    	for(int i = 0; i < wishlist.size(); i++) {
    		array.put();
    	}*/
    	result.put("wishlist", "wisl");
    	return new JSONMessage("Get User Wishlist Result", result.toString());
    }
    
    public JSONMessage handleJSONRequest(JSONMessage request) {
    	switch(request.message) {
    		case "login":
    			return handleLoginRequest(request.data);
    		case "add product":
    			return handleAddProductRequest(request.data);
    		case "remove product":
    			return handleRemoveProductRequest(request.data);
    		case "get user wishlist":
    			return handleGetUserWishlistRequest(request.data);
    		default:
    			JSONObject obj = new JSONObject();
    			obj.put("msg", "Invalid Request");
    			return new JSONMessage("Error", obj.toString());
    	}
    	
    	
    }

    

}
