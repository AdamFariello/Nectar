package server.application;

public class ProductVO extends ValueObject{
    public String productID;
    public boolean available;
    public double price; 

    @Override
    protected String[] getEqualityComponents() {
        String[] s = {productID, Boolean.toString(available), Double.toString(price)};
        return s;    
    }
    
}
