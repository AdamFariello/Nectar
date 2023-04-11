package server.application;

public class AmazonScraperCommand extends ScraperCommand{

    public AmazonScraperCommand(String productID, String url, Receiver receiver) {
        super(productID, url, receiver);
    }

    @Override
    protected void execute() {
        // TODO scrape amazon website, create productVO and call receiver.receive(productVO)
    }
    
}
