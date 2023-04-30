public class test {
    public static void main(String[] args) {
        ProductVO prev = new ProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", false, 10, 0);
        ProductVO curr = new ProductVO("Beats Studio3 Wireless Noise Cancelling Over-Ear Headphones", "0", true, 200, 15);
        Receiver receiver = new Receiver();
        receiver.receive(prev);
        receiver.receive(curr);
    }
}
