package bl;
import java.util.HashMap;

import org.json.*;

import server.JSONMessage;


public class UserDelegate {
    private ProductTracker productTracker;
    private UserDao userDao;

    public UserDelegate(ProductTracker productTracker, UserDao userDao){
        this.productTracker = productTracker;
        this.userDao = userDao;
    }

    public void addProductToUserWishList(String userID, String url, UserTrackerSettingsVO settings){
        //Add product to wishlist in database, if product is not in database add it and get productID, 
        ///with product id add to product tracker
    }

    public JSONMessage handleGetProductData(JSONObject data){
    	String productData = productTracker.getProductData((String)data.get("ProductID")).encode();
        return new JSONMessage("ProductData", productData);
    }
    
    private JSONMessage handleLoginRequest(JSONObject data) {
    	try {
    		JSONObject result = new JSONObject();
        	// access database to check if password in data object is valid
    		if(data.get("Username").toString().equals("User") && data.get("Password").toString().equals("Pass")) {
        		//System.out.println("success");
        		result.put("result", true);
        		result.put("UserID", 1);
        	}else {
        		//System.out.println("wrong login");
        		result.put("result", false);
        	}
        	return new JSONMessage("Login Result", result.toString());
    	}catch (Exception e) {
    		JSONObject obj = new JSONObject();
    		obj.put("msg", "Invalid Login Request Data");
			return new JSONMessage("Error", obj.toString());
    	}	
    }
    
    public JSONMessage handleJSONRequest(JSONMessage request) {
    	switch(request.message) {
    		case "login":
    			return handleLoginRequest(request.data);
    		default:
    			JSONObject obj = new JSONObject();
    			obj.put("msg", "Invalid Request");
    			return new JSONMessage("Error", obj.toString());
    	}
    	
    	
    }

    

}
