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

    public void receive(ProductVO productVO){
        if (previousProductVO == null){
            previousProductVO = productVO;
        } else if (previousProductVO != productVO){
            userSubscribers.forEach((k, v) -> v.notify(productVO, previousProductVO));
        }
    }
}
