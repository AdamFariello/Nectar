public class ProductVO extends ValueObject{
    public String productID;
    public boolean available;
    public double price; 

    public ProductVO(String productID, boolean available, double price){
        this.productID = productID;
        this.available = available;
        this.price = price;
    }

    @Override
    protected String[] getEqualityComponents() {
        String[] s = {productID, Boolean.toString(available), Double.toString(price)};
        return s;    
    }
    
}
