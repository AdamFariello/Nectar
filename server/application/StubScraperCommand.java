public class StubScraperCommand implements ScraperCommand {
    private ProductVO first;
    private ProductVO second;
    private boolean before;
    public StubScraperCommand(String productID, String url, Receiver receiver) {
        this.first = new ProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", false, 10, 0);
        this.second = new ProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", true, 200, 15);
        ths.before = true;
        super(productID, url, receiver);
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
}
