import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private static ServerSocket server;
    private static int port = 8999;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server;
        Socket client;
        InputStream input;
        while(true) {
            server = new ServerSocket(port);
            System.out.println("server created");
            client = server.accept();
            System.out.println("client connected");

            input = client.getInputStream();
            String inputString = inputStreamAsString(input);
            System.out.println(inputString);
            
            client.close();
            server.close();
        }
    }

    public static String inputStreamAsString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }

        br.close();
        return sb.toString();
    }
}
