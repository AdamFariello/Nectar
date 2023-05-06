package tests;
import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import bl.ProductTracker;
import bl.UserDao;
import bl.UserDelegate;
import server.JSONMessage;
import junit.framework.*;

public class UserDelegateUnitTests {
	private UserDelegate delegate;
	
	@Before
	public void setupDelegate() {
		ProductTracker tracker = new ProductTracker();
        delegate = new UserDelegate(tracker, new UserDaoStub());
	}
	@Test
	public void testLoginError() {
		JSONObject wrongLoginData = new JSONObject();
		wrongLoginData.put("Error", "hi");
		JSONMessage request = new JSONMessage("login", wrongLoginData.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Login Error", result.message);
		assertEquals("Invalid Login Request Data", result.data.get("Error Message"));
	}
	
	@Test
	public void testLoginWrongPasswordOrInvalidUsername() {
		JSONObject wrongPassword = new JSONObject();
		wrongPassword.put("Username", "john@gmail.com");
		wrongPassword.put("Password", "wrongPass");
		JSONMessage request = new JSONMessage("login", wrongPassword.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Login Result", result.message);
		assertEquals(false, result.data.get("result"));	
		
		JSONObject invalidUsername = new JSONObject();
		invalidUsername.put("Username", "jason@gmail.com");
		invalidUsername.put("Password", "Pass");
		JSONMessage request2 = new JSONMessage("login", invalidUsername.toString());
		JSONMessage result2 = delegate.handleJSONRequest(request2);
		assertEquals("Login Error", result2.message);
		assertEquals("Invalid Username", result2.data.get("Error Message"));	
	}
	
	@Test
	public void testCorrectLogin() {
		JSONObject correctLoginData = new JSONObject();
		correctLoginData.put("Username", "john@gmail.com");
		correctLoginData.put("Password", "Pass");
		JSONMessage request = new JSONMessage("login", correctLoginData.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Login Result", result.message);
		assertEquals(true, result.data.get("result"));	
		int userID = (int) result.data.get("UserID");
		assertTrue(userID >= 1);
	}
	
	@Test
	public void testAddProductValid() {
		JSONObject addProduct = new JSONObject();
		addProduct.put("UserID", "123");
		addProduct.put("ProductID", "234");
		addProduct.put("Url", "https://www.amazon.com/Nintendo-Switch-Lite-Blue/dp/B092VT1JGD/ref=sr_1_5?keywords=nintendo%2Bswitch&qid=1681979388&sprefix=nin%2Caps%2C82&sr=8-5&th=1");
		addProduct.put("Website", "Amazon");
		JSONMessage request = new JSONMessage("add product", addProduct.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Add Product Result", result.message);
		assertEquals(true, result.data.get("result"));	
		
		JSONObject addUntrackedProduct = new JSONObject();
		addProduct.put("UserID", "123");
		addProduct.put("ProductID", "");
		addProduct.put("Url", "https://www.amazon.com/Test-5-Nintendo-Switch/dp/B09YZXLYG4/ref=sr_1_2?keywords=nintendo%2Bswitch&qid=1681979362&sprefix=nin%2Caps%2C82&sr=8-2&th=1");	
		addProduct.put("Website", "Amazon");
		JSONMessage request2 = new JSONMessage("add product", addUntrackedProduct.toString());
		JSONMessage result2 = delegate.handleJSONRequest(request2);
		assertEquals("Add Product Result", result2.message);
		assertEquals(true, result2.data.get("result"));	
		int productID = (int) result.data.get("ProductID");
		assertTrue(productID > 1);
	}
	
	@Test
	public void testAddProductInvalid() {
		JSONObject AddProductInvalid = new JSONObject();
		AddProductInvalid.put("Username", "john@gmail.com");
		AddProductInvalid.put("Password", "Pass");
		JSONMessage request = new JSONMessage("add product", AddProductInvalid.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Add Product Error", result.message);
		assertEquals("Invalid Add Product Request Data", result.data.get("Error Message"));	
	}
	
	@Test
	public void testRemoveProduct() {
		JSONObject addProduct = new JSONObject();
		addProduct.put("UserID", "123");
		addProduct.put("ProductID", "234");
		addProduct.put("Url", "https://www.amazon.com/Nintendo-Switch-Lite-Blue/dp/B092VT1JGD/ref=sr_1_5?keywords=nintendo%2Bswitch&qid=1681979388&sprefix=nin%2Caps%2C82&sr=8-5&th=1");
		addProduct.put("Website", "Amazon");
		delegate.handleJSONRequest(new JSONMessage("add product", addProduct.toString()));
		
		JSONObject RemoveProduct = new JSONObject();
		RemoveProduct.put("UserID", "123");
		RemoveProduct.put("ProductID", "234");
		JSONMessage request = new JSONMessage("remove product", RemoveProduct.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Remove Product Result", result.message);
		assertEquals(true, result.data.get("result"));
		
		JSONObject RemoveProductUnsuccessful = new JSONObject();
		RemoveProduct.put("UserID", "123");
		RemoveProduct.put("ProductID", "123");
		JSONMessage request2 = new JSONMessage("remove product", RemoveProductUnsuccessful.toString());
		JSONMessage result2 = delegate.handleJSONRequest(request);
		assertEquals("Remove Product Result", result.message);
		assertEquals(false, result.data.get("result"));	
	}
	
	@Test
	public void testInvalidRequest() {
		JSONObject RemoveProduct = new JSONObject();
		RemoveProduct.put("UserID", "123");
		RemoveProduct.put("ProductID", "234");
		JSONMessage request = new JSONMessage("Bad request", RemoveProduct.toString());
		JSONMessage result = delegate.handleJSONRequest(request);
		assertEquals("Error", result.message);
		assertEquals("Invalid Request", result.data.get("msg"));	
	}
	
	@Test
	public void testGetUserWishlistRequest() {
		
	}
}
