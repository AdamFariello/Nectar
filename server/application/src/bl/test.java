package bl;
public class test {
    public static void main(String[] args) {
        ScrapedProductVO prev = new ScrapedProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", false, 10, 0);
        ScrapedProductVO curr = new ScrapedProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", true, 200, 15);
        Receiver receiver = new Receiver();
        receiver.receive(prev);
        receiver.receive(curr);
    }
}
