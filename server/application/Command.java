package server.application;

public abstract class Command {
    private Receiver receiver;
    private String productID;
    private String url;

    public Command(String productID, String url, Receiver receiver){
        this.productID = productID;
        this.url = url;
        this.receiver = receiver;
    }
    
    protected abstract void execute();
}
