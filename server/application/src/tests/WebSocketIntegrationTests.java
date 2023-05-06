package tests;

import java.io.IOException;
import java.net.URI;
import bl.*;
import org.eclipse.jetty.websocket.api.util.WSURI;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.Before;
import org.junit.Test;

import server.WebSocketServer;


public class WebSocketIntegrationTests {
    private WebSocketServer server;
    private ProductTracker tracker;
    private UserDelegate delegate;
    private Session session;
    
    @Before
    public void startServer() throws Exception {
    	tracker = new ProductTracker();
        delegate = new UserDelegate(tracker, new UserDao());
    	server = new WebSocketServer(tracker, delegate);
    	server.setPort(8080);
        server.start();
        server.join();
        createTestConnection();
    }
    
    public void createTestConnection() throws Exception{
        //URI destUri = server.getURI().resolve("/nectar/");
        //URI wsUri = WSURI.toWebsocket(destUri);
    	URI uri = URI.create("ws://localhost:8080/nectar");
        WebSocketClientStub client = new WebSocketClientStub();
        session = client.connect(uri, tracker, delegate);
        
    }
    
    @Test
    public void testLogin() throws IOException {
    	//session.getRemote().sendString();
    }
    
    @Test
    public void testNotifyUserOnProductChange() {
    	UserVO user = new UserVO("1", "", "", "");
    	tracker.addUser(user, "1", "testurl", "Stub");
        tracker.trackProducts();
        tracker.trackProducts();
    }

}
