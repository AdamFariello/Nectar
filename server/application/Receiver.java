package server.application;

import java.util.HashMap;

public class Receiver {
    HashMap<String, UserSubscriber> userSubscribers;
    ProductVO previousProductVO;

    public void addUser(String userID, UserSubscriber userSubscriber){
        userSubscribers.put(userID, userSubscriber);
    }

    //Returns if there are no more users left to notify
    public boolean removeUser(String userID){
        if (userSubscribers.size() == 0){
            return true;
            
        }else if (userSubscribers.size() == 1){
            userSubscribers.remove(userID);
            return true;
        }else{
            userSubscribers.remove(userID);
            return false;
        }
    }

    /*TODO: build message to notify user publisher that product info has changed. 
        Publisher will know productTrackerSettings for each user and based on that
        will notify each user of the change in product info. Message will tell publisher
        the product id and for every change in info will give it the prev value and the current val
        ex. "ProductID:  xxxxx, Price: $24 - $55, Avaiability: True - False, Discount: 0% - 10%"
    */
    public void receive(ProductVO productVO){
        if (previousProductVO == null){
            previousProductVO = productVO;
        } else if (previousProductVO != productVO){
            userSubscribers.forEach((k, v) -> v.notify(productVO, previousProductVO));
        }
    }
}
