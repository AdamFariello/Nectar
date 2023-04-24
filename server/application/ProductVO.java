public class ProductVO extends ValueObject{
    public String productID;
    public boolean available;
    public double price; 
    public int amtInStock;

    public ProductVO(String productID, boolean available, double price, int amtInStock){
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
