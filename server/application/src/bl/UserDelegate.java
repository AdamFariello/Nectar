package bl;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

import databaseCode.DBConnetion;
import databaseCode.DBQuery;
import databaseCode.DBRetrieve;
import server.JSONMessage;


public class UserDelegate {
	private static String [] dbs = {
			"nectarDB_administration", 
			"nectarDB_products", 
			"nectarDB_user"
		};
    private ProductTracker productTracker;
    private UserDao userDao;
    private static DBConnetion con = null;
	private static DBQuery query = null;

    public UserDelegate(ProductTracker productTracker, UserDao userDao){
        this.productTracker = productTracker;
        this.userDao = userDao;
        con = new DBConnetion();
		con.startConnection(dbs[2]);		
		query = new DBQuery(con);
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
    		String username = data.get("Username").toString();
    		String password = data.get("Password").toString();
    		JSONObject result = new JSONObject();
    		ArrayList<String> columns = new ArrayList<String>();
    		columns.add("user_id");
    		columns.add("user_email");
    		columns.add("user_password");
    		ArrayList<String> wheres = new ArrayList<String>();
    		wheres.add("user_email");
    		ArrayList<String> wheresValues = new ArrayList<String>();
    		wheresValues.add(data.get("Username").toString());
    		
    		query.setCurrentServer(dbs[2]);
    		ArrayList<ArrayList<String>> userData = query.getFromTable_2DArrStr("nectarDB_user", columns, wheres, wheresValues);
    		if(!userData.isEmpty()) {
    			
    		}
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
    		obj.put("Error Message", "Invalid Login Request Data");
			return new JSONMessage("Error", obj.toString());
    	}	
    }
    
    private JSONMessage handleAddProductRequest(JSONObject data) {
    	String userID = data.get("UserID").toString();
    	String productID = data.get("ProductID").toString();
    	String url = data.getString("Url").toString();
    	String website = data.getString("Website").toString();
    	boolean success = productTracker.addUser(userID, productID, url, website);
    	JSONObject result = new JSONObject();
		result.put("result", success);
    	return new JSONMessage("Add Product Result", result.toString());
    }
    
    private JSONMessage handleRemoveProductRequest(JSONObject data) {
    	String userID = data.get("UserID").toString();
    	String productID = data.get("ProductID").toString();
    	boolean success = productTracker.removeUser(userID, productID);
    	JSONObject result = new JSONObject();
    	result.put("result", success);
    	return new JSONMessage("Remove Product Result", result.toString());
    }
    
    private JSONMessage handleGetUserWishlistRequest(JSONObject data) {
    	String userID = data.get("UserID").toString();
    	JSONObject result = new JSONObject();
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
    		case "get product wishlist":
    			return handleGetUserWishlistRequest(request.data);
    		default:
    			JSONObject obj = new JSONObject();
    			obj.put("msg", "Invalid Request");
    			return new JSONMessage("Error", obj.toString());
    	}
    	
    	
    }

    

}
