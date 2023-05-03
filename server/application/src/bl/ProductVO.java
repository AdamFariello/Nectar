package bl;
public class ProductVO extends ValueObject{
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
    }

    @Override
    protected String[] getEqualityComponents() {
        String[] s = {title, productID, Boolean.toString(available), Double.toString(price), Integer.toString(amtInStock)};
        return s;    
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
