package bl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import databaseCode.*;

public class UserDao {
    String [] dbs = {
        "nectarDB_administration", 
        "nectarDB_products", 
        "nectarDB_user"
    };
    
    public void initializeDatabaseWithSampleUsers() {
    	//Add sample users with username and password here 
    	int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
		String table = "user";
	    
	    for (int i = 0; i < 10; i++) {
		    Random random = new Random();	
		    String generatedString1 = random.ints(leftLimit, rightLimit + 1)
										    .limit(targetStringLength)
										    .collect(StringBuilder::new, 
												     StringBuilder::appendCodePoint, 
												     StringBuilder::append)
										    .toString();
		    String generatedString2 = random.ints(leftLimit, rightLimit + 1)
										    .limit(targetStringLength)
										    .collect(StringBuilder::new, 
												     StringBuilder::appendCodePoint, 
												     StringBuilder::append)
										    .toString();
		    
		    ArrayList<Object> tableInputs = new ArrayList<Object>();
		    tableInputs.add(generatedString1); tableInputs.add(generatedString2);
		    test.insertIntoStrongTable_WithOutPrimaryKey_ArrObj(table, tableInputs);
	    }
	    
	    //DEBUG
	    //System.out.println(test.getFromTable_2DArrStr(table));
    }
    
    public HashMap<String, ArrayList<String>> getAllUserWishLists() {
    	//return a hashmap mapping user ids to a list of all the product ids they track
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
    	
		String table = "userWishList";
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("user_id");		
		String groupBy = "user_id";
		 
		ArrayList<ArrayList<String>> list = test.getFromTable_2DArrStr(
												table, columns, groupBy
											 );		
		HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
		String userID = null;
		ArrayList<String> userList = new ArrayList<String>();
		for (ArrayList<String> row: list) {
			if (userID == null) {
				userID = row.get(0);
				userList.add(row.get(1));
			} else if (userID != row.get(0)) { 
				userID = row.get(0);
				hashMap.put(userID, userList);
				userList.clear();
			} else {
				userList.add(row.get(1));
			}
		}

		con.endConnection();
		if (hashMap.isEmpty()) {return null;}
		else {return hashMap;}
    }
    
    public ArrayList<String> getUserWishList(String userID){
    	//return a list of all the product ids a user tracks
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
		DBQuery test = new DBQuery(con);
		
		String table = "userWishList"; 
		ArrayList<String> columns = new ArrayList<String>(); 
		columns.add("product_id");
		
		ArrayList<String> wheres = new ArrayList<String>();
		wheres.add("user_id");
		
		ArrayList<String> wheresValues = new ArrayList<String>();
		wheresValues.add(userID);
		
		
		DBConversions<String> DBConversion = new DBConversions<String>();
		ResultSet rs = test.getFromTable_RS(table, columns, wheres, wheresValues);
		con.endConnection();
		return DBConversion.convertColumn(rs);	
	}
    
    public ProductVO getProductInfoByProductID(String productID) {
    	//What to do:
    	//	1) Return: product url, sitename 
    	//	2) Make a productVO, setting the public variables with what you got
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[1]);		
		DBQuery test = new DBQuery(con);
    	
    	String table = "product"; 
    	
    	//ArrayList<String> columns = new ArrayList<String>();
    	//columns.add("product_siteName"); columns.add("product_siteURL");
    	
    	ArrayList<String> wheres = new ArrayList<String>();
    	wheres.add("product_id");
    	
    	ArrayList<String> wheresValues = new ArrayList<String>();
    	wheresValues.add(productID);
    	
    	ArrayList<String> list = (test.getFromTable_2DArrStr(table, wheres, wheresValues)).get(0);
    	
    	con.endConnection();
    	return new ProductVO (Integer.parseInt(productID), list.get(1), list.get(2));
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
    	
    	ArrayList<String> user = (test.getFromTable_2DArrStr(table, columns, wheres, wheresValues)).get(0);
    	
    	con.endConnection();
    	return new UserVO(user.get(0), user.get(1), user.get(2), user.get(3));
    }
    
    public Boolean addProductToUserWishlist(String userID, ProductVO product){
    	//add product to user's wish-list and return if its successful or not 
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
			
			
			ArrayList<ArrayList<Object>> temp = test.getFromTable_2DArrObj(
				table, columns, wheres, wheresValues
			);
			int product_id = (Integer) temp.get(0).get(0);
			
			
			test.setCurrentServer(dbs[1]);
			String weakTable = "userWishList";
			ArrayList<Object> strongTablePrimaryKeys = new ArrayList<Object>();
			strongTablePrimaryKeys.add(Integer.parseInt(product_siteUrl)); 
			strongTablePrimaryKeys.add(product_id);
			bool = test.insertIntoWeakTable_ArrObj(weakTable, strongTablePrimaryKeys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		con.endConnection();
		return bool;
    }
    
    public boolean removeProductFromUserWishlist(int userID, String productID){
    	//remove product to user's wish-list and return if its successful or not
    	DBConnetion con = new DBConnetion();
    	con.startConnection(dbs[2]);		
    	DBEdit<Object> dbE = new DBEdit<Object>(con);
    	
    	String table = "userWishList";
    	ArrayList<Object> columns = new ArrayList<Object>();
    	columns.add("product_id");
    	ArrayList<Object> values  = new ArrayList<Object>();
    	values.add(productID);
    	
    	return dbE.delete(productID, null, null);
    }

    public String addUser(UserVO user) {
    	//add user to database return its userID
		DBConnetion con = new DBConnetion();
		con.startConnection(dbs[1]);
		DBQuery test = new DBQuery(con);
		
		String table = "user";
		
		ArrayList<Object> tableInputs = new ArrayList<>(
											Arrays.asList(user.getEqualityComponents()
										));
		test.insertIntoStrongTable_WithOutPrimaryKey_ArrObj(table, tableInputs);
		
		ArrayList<String> columns = new ArrayList<String>(); 
		columns.add("user_id");
		ArrayList<String> wheres = new ArrayList<String>();
		wheres.add("user_email"); wheres.add("user_password");
		ArrayList<String> wheresValues = new ArrayList<>(
											Arrays.asList(user.getEqualityComponents())
										 );
		wheresValues.remove(0);
		String s = test.getFromTable_2DArrStr(table, columns, wheres, wheresValues).get(0).get(0);
		
    	con.endConnection();
    	return s;
    }

    
    //TODO: We can decide on more later on
}
