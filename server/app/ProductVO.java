package server.app;
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
        String[] s = {productID, Boolean.toString(available), Double.toString(price)};
        return s;    
    }
    
}
