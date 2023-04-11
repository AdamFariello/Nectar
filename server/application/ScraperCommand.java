package server.application;

public abstract class ScraperCommand {
    protected Receiver receiver;
    protected String productID;
    protected String url;

    public ScraperCommand(String productID, String url, Receiver receiver){
        this.productID = productID;
        this.url = url;
        this.receiver = receiver;
    }
    
    protected abstract void execute();
}
