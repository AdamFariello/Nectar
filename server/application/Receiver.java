package server.application;

public class Receiver {
    ProductVO previousProductVO;
    //Add user publisher list for product here

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
            String msg = "";
        }
    }
}
