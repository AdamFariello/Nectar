package server.application;

public class AmazonScraperCommand extends ScraperCommand{

    public AmazonScraperCommand(String productID, String url, Receiver receiver) {
        super(productID, url, receiver);
    }

    @Override
    protected void execute() {
        // TODO call s and call receiver.receive(productVO)
        receiver.receive(scrape());
    }

    @Override
    protected ProductVO scrape() {
        // TODO scrape amazon website, create and return product VO
        return null;
    }
    
}
