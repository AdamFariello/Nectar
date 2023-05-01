package server.tests;

import server.app.ProductVO;
import server.app.Receiver;
import server.app.ScraperCommand;

public class StubScraperCommand extends ScraperCommand {
    private ProductVO first;
    private ProductVO second;
    private boolean before;

    public StubScraperCommand(String productID, String url, Receiver receiver) {
        super(productID, url, receiver);
        this.first = new ProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", false, 10, 0);
        this.second = new ProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", true, 200, 15);
        this.before = true;
    }

    @Override
    protected void execute() {
        // TODO call s and call receiver.receive(productVO)
        if(this.before){
            receiver.receive(first);
            this.before = false;
        }else{
            receiver.receive(second);
        }
        
    }

    @Override
    protected ProductVO scrape() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scrape'");
    }
}
