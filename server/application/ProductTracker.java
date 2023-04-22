package server.application;

import java.util.HashMap;

public class ProductTracker {
    private WebScraper webScraper;
    HashMap<String, Receiver> receivers;

    public ProductTracker(){
        webScraper = new WebScraper();
    }

    private void addProduct(String productID, String url, Receiver receiver){
        receivers.put(productID, receiver);
    }

    private void removeProduct(String productID){
        webScraper.removeProduct(productID);
        receivers.remove(productID);
    }

    public void addUser(String userID, UserSubscriber userSubscriber, String productID){
        if (!receivers.containsKey(productID)){
            Receiver receiver = new Receiver();
            receiver.addUser(userID, userSubscriber);
            addProduct(productID, productID, receiver);
        }else{
            receivers.get(productID).addUser(userID, userSubscriber);
        }
    }

    public void removeUser(String userID, String productID){
        boolean noUsersLeft = receivers.get(productID).removeUser(userID);
        if (noUsersLeft){
            removeProduct(productID);
        }
    }

    public void trackProducts(){
        webScraper.scrape();
    }
}
