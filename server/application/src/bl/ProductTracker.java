package bl;
import java.util.HashMap;

import server.EventEndpoint;

public class ProductTracker implements Runnable{
	private Thread t;
    private WebScraper webScraper;
    HashMap<String, Receiver> receivers;

    public ProductTracker(){
        webScraper = new WebScraper();
    }

    private void addProduct(String productID, String url, String website, Receiver receiver){
        receivers.put(productID, receiver);
        webScraper.addProduct(productID, url, website, receiver);
    }

    private void removeProduct(String productID){
        webScraper.removeProduct(productID);
        receivers.remove(productID);
    }

    public boolean addUser(String userID, String productID, String url, String website){
        if (!receivers.containsKey(productID)){
            Receiver receiver = new Receiver();
            receiver.addUser(userID);
            if(webScraper.checkWebsiteSupport(website)) {
            	addProduct(productID, url, website, receiver);
            }else {
            	return false;
            }
            
        }else{
            receivers.get(productID).addUser(userID);
        }
        return true;
    }
    //TODO: make this method accept a value object array for the product info to make command objects for them right now
    //This isn't functional
    public void addUserWishList(String userID, String[] wishlist){
        for(String productID : wishlist){
            addUser(userID, productID, "Place Holder", "Place Holder");
        }

    }

    public boolean removeUser(String userID, String productID){
        boolean noUsersLeft = receivers.get(productID).removeUser(userID);
        if (noUsersLeft){
            removeProduct(productID);
        }
        return true;
    }

    public void removeUserWishList(String userID, String[] wishlist){
        for(String productID : wishlist){
            removeUser(userID, productID);
        }
    }

    public void trackProducts(){
        webScraper.scrape();
    }

    public ScrapedProductVO getProductData(String productID){
        return webScraper.getProductData(productID);
    }
    
    public ScrapedProductVO getProductDataFromUrl(String url, String website){
        return webScraper.getProductDataFromUrl(url, website);
    }

	public void setEndpoint(EventEndpoint eventEndpoint, String currentSessionUserID) {
		receivers.forEach((k, v) -> v.setEndpoint(eventEndpoint, currentSessionUserID));		
	}

	public void closeEndpoint() {
		receivers.forEach((k, v) -> v.closeEndpoint());
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
