<<<<<<< HEAD

=======
>>>>>>> 83223f9 (fixed package error)
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        File file = new File("scripts/scraper.py");
        String path = file.getAbsolutePath();
        ProcessBuilder processBuilder = new ProcessBuilder("python", path);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            
            boolean available = false;
            double price = -1.0; 
            int amtInStock = -2;
            String title = "";
            
            String line;
            while((line = r.readLine()) != null){
                if(line.startsWith("Product Title")){
                    title = line.substring(line.indexOf("=") + 2);

                }else if (line.startsWith("Product Price")){
                    price = Double.parseDouble(line.replaceAll("\\D+", ""));

                }else if(line.startsWith("Availability")){
                    if(line.endsWith("In Stock")){
                        available = true;
                        amtInStock = -1;
                    }else if(line.startsWith("Only")){
                        available = true;
                        amtInStock = Integer.parseInt(line.replaceAll("\\D+", ""));
                    }else{
                        available = false;
                        amtInStock = 0;
                    }
                }
            }
            return new ProductVO(title, productID, available, price, amtInStock);
        } catch (IOException e) {
            return null;
        }
    }
    
}
