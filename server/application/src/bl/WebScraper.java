package bl;
import java.util.HashMap;

import tests.ScraperCommandStub;

public class WebScraper {
    HashMap<String, ScraperCommand> scraperCommands;
    private static String[] supportedWebsites = {"Amazon", "Stub"};

    public WebScraper(){
    	scraperCommands = new HashMap<String, ScraperCommand>();
    }
    public boolean addProduct(String productID, String url, String website, Receiver receiver){
        switch (website) {
            case "Amazon": 
                scraperCommands.put(productID, new AmazonScraperCommand(productID, url, receiver));
                return true;
            case "Stub":
            	scraperCommands.put(productID, new ScraperCommandStub(productID, url, receiver));
            default:
            	return false;
        }
    }
    
    public boolean checkWebsiteSupport(String website) {
    	for (String s : supportedWebsites) {
    		if (website.equals(s)) {
    			return true;
    		}
    	}
    	return false;
    }

    public void removeProduct(String productID){
        scraperCommands.remove(productID);
    }

    public void scrape(){
        scraperCommands.forEach((k, v) -> v.execute());
    }

    public ScrapedProductVO getProductData(String productID){
        return scraperCommands.get(productID).scrape();
    }
}
