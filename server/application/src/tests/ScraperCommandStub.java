package tests;

import bl.ScrapedProductVO;
import bl.Receiver;
import bl.ScraperCommand;

public class ScraperCommandStub extends ScraperCommand {
    private ScrapedProductVO first;
    private ScrapedProductVO second;
    private boolean before;

    public ScraperCommandStub(String productID, String url, Receiver receiver) {
        super(productID, url, receiver);
        this.first = new ScrapedProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", false, 10, 0);
        this.second = new ScrapedProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", true, 200, 15);
        this.before = true;
    }

    @Override
    protected void execute() {
        // TODO call s and call receiver.receive(productVO)
        if(this.before){
            receiver.receive(first);
            this.before = false;
            System.out.println("first");
        }else{
            receiver.receive(second);
            //this.before = true;
            System.out.println("second");
        }
        
    }

    @Override
	public ScrapedProductVO scrape() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scrape'");
    }
}
