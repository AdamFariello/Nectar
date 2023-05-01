package bl;
import java.util.HashMap;

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

    public ProductVO getProductData(String productID){
        return productTracker.getProductData(productID);
    }
}
