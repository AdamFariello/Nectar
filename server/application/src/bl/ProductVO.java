package bl;

import org.json.simple.JSONObject;

public class ProductVO extends JSONEncodeableValueObject{
    public String title;
    public String productID;
    public boolean available;
    public double price; 
    public int amtInStock;

    public ProductVO(String title, String productID, boolean available, double price, int amtInStock){
        this.title = title;
        this.productID = productID;
        this.available = available;
        this.price = price;
        this.amtInStock = amtInStock;
        this.type = "ProductVO";
    }

    @Override
    protected String[] getEqualityComponents() {
        String[] s = {title, productID, Boolean.toString(available), Double.toString(price), Integer.toString(amtInStock), type};
        return s;    
    }
    
    @Override
    protected String encode() {
    	JSONObject obj = new JSONObject();
		obj.put("title", title);
		obj.put("productID", productID);
		obj.put("available", available);
		obj.put("price", price);
		obj.put("amtInStock", amtInStock);
		return obj.toString();
    }
    
    @Override
    public String toString() {
    	String res = "";
    	for (String s: getEqualityComponents()){
    		res = res + s;
    		res = res + "\n";
    	}
    	return res;
    }
    
}
