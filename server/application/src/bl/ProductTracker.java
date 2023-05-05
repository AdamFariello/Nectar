package bl;
import java.util.HashMap;

import server.EventEndpoint;

public class ProductTracker implements Runnable{
	private Thread t;
    private WebScraper webScraper;
    HashMap<String, Receiver> receivers;

    public ProductTracker(){
        webScraper = new WebScraper();
        receivers = new HashMap<String, Receiver>();
    }

    private void addProduct(String productID, String url, String website, Receiver receiver){
        receivers.put(productID, receiver);
        webScraper.addProduct(productID, url, website, receiver);
    }

    private void removeProduct(String productID){
        webScraper.removeProduct(productID);
        receivers.remove(productID);
    }

    public void addUser(String userID, String productID, String url, String website){
        if (!receivers.containsKey(productID)){
            Receiver receiver = new Receiver();
            receiver.addUser(userID);
            addProduct(productID, url, website, receiver);
        }else{
            receivers.get(productID).addUser(userID);
        }
    }
    //TODO: make this method accept a value object array for the product info to make command objects for them right now
    //This isn't functional
    public void addUserWishList(String userID, String[] wishlist){
        for(String productID : wishlist){
            addUser(userID, productID, "Place Holder", "Place Holder");
        }

    }
    
    public void setEndpoint(EventEndpoint endpoint, String userID) {
    	receivers.forEach((k, v) -> v.setEndpoint(endpoint, userID));
    }
    
    public void closeEndpoint() {
    	receivers.forEach((k, v) -> v.closeEndpoint());
    }

    public void removeUser(String userID, String productID){
        boolean noUsersLeft = receivers.get(productID).removeUser(userID);
        if (noUsersLeft){
            removeProduct(productID);
        }
    }

    public void removeUserWishList(String userID, String[] wishlist){
        for(String productID : wishlist){
            removeUser(userID, productID);
        }
    }

    public void trackProducts(){
        webScraper.scrape();
    }

    public ProductVO getProductData(String productID){
        return webScraper.getProductData(productID);
    }

	@Override
	synchronized public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				System.out.println("Tracking");
				trackProducts();
				wait(1000);	
			} catch (InterruptedException ex) {
		        Thread.currentThread().interrupt();
		    }
		}
	}
	
	public void start() {
		//System.out.println("Starting");
	     if (t == null) {
	    	 t = new Thread (this, "tracker");
	         t.start ();
	     }
	}
}
