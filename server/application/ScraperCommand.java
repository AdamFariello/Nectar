package server.application;

public abstract class ScraperCommand {
    private Receiver receiver;
    private String productID;
    private String url;

    public ScraperCommand(String productID, String url, Receiver receiver){
        this.productID = productID;
        this.url = url;
        this.receiver = receiver;
    }
    
    protected abstract void execute();
}
